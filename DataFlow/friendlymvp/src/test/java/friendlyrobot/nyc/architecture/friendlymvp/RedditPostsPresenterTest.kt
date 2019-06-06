package friendlyrobot.nyc.architecture.friendlymvp

import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.Store
import com.squareup.moshi.Moshi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okio.Okio
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat
import java.io.InputStream

class RedditPostsPresenterTest() {

    @Test
    fun showIsCalled() {

        val redditData = data()
        val store = mockk<Store<RedditData, BarCode>>()
        val friendlyRVMVPView = mockk<FriendlyRVMVPView>()
        val barcode = BarCode(RedditData::class.java.simpleName, "aww")
        val redditPostsPresenter = RedditPostsPresenter(store, barcode, Schedulers.trampoline(), Schedulers.trampoline())

        every { store.get(barcode) } returns Single.just(redditData)
        every { friendlyRVMVPView.showLoading() } returns Unit

        redditPostsPresenter.attachView(friendlyRVMVPView)

        verify {
            friendlyRVMVPView.showLoading()
            friendlyRVMVPView.show(any())
        }
    }

    @Test
    fun dataIsParsed() {

        val redditData = data()
        val store = mockk<Store<RedditData, BarCode>>()
        val barcode = BarCode(RedditData::class.java.simpleName, "aww")
        val redditPostsPresenter = RedditPostsPresenter(store, barcode, Schedulers.trampoline(), Schedulers.trampoline())

        every { store.get(barcode) } returns Single.just(redditData)

        var testposts: List<Post>? = null

        val friendlyRVMVPView = object : FriendlyRVMVPView {
            override fun show(posts: List<Post>) {
                testposts = posts
            }

            override fun showLoading() {}
        }

        redditPostsPresenter.attachView(friendlyRVMVPView)
        assertThat(testposts).isNotNull()
        assertThat(testposts).size().isEqualTo(39)
    }

    private fun data() : RedditData {
        val myStream = inputStream("/reddit_data.json")
        assertThat(myStream).isNotNull()
        val moshi = Moshi.Builder().build()
        val redditData = moshi.adapter<RedditData>(RedditData::class.java).fromJson(Okio.buffer(Okio.source(myStream)))
        assertThat(redditData).isNotNull()
        return redditData!!
    }

    private fun inputStream(path: String): InputStream {
        return RedditPostsPresenterTest::class.java!!.getResourceAsStream(path)
    }

}