package friendlyrobot.nyc.architecture.friendlymvp

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.android.external.store3.base.impl.BarCode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface FriendlyRVMVPView : MvpView {
    fun show()
    fun hide()
}


class FriendlyRV @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle), FriendlyRVMVPView {



    override fun show() {

    }

    override fun hide() {

    }


    init {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager = linearLayoutManager

    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val store = AppModule.provideStore(context)


        val awwRequest = BarCode(RedditData::class.java.simpleName, "aww")

        store.fetch(awwRequest)
            .flatMapObservable {sanitizeData(it) }
            .filter{
                //https://v.redd.it/hx02o949in231
                //https://imgur.com/zbp0oTF
                var emit = true
                if (it.url.startsWith("https://v.redd.it") || it.url.startsWith("https://imgur.com/")) {
                    emit = false
                }
                emit

            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                    it ->
                Log.e("foo", it.toString())
            },{
                    it ->
                Log.e("Foo", it.message, it)
            })

    }

     fun sanitizeData(redditData: RedditData): Observable<Post> {
        return Observable.fromIterable(redditData?.data?.children).map { it.data }
    }
}