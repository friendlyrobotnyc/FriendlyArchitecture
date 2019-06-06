package friendlyrobot.nyc.architecture.friendlymvp

import android.content.Context
import com.nytimes.android.external.fs3.SourcePersisterFactory
import com.nytimes.android.external.store3.base.Persister
import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.moshi.MoshiParserFactory
import com.squareup.moshi.Moshi
import okio.BufferedSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

object AppModule {

    private var store : Store<RedditData, BarCode>? = null

    fun provideApi() : Api {
        return Retrofit.Builder()
            .baseUrl("https://reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .validateEagerly(BuildConfig.DEBUG)  // Fail early: check Retrofit configuration at creation time in Debug build.
            .build()
            .create(Api::class.java)
    }

    fun provideStore(context: Context) : Store<RedditData, BarCode> {

        if (store == null) {
            store = providePersistedStore(context)
        }
        return store!!
    }

    private fun providePersistedStore(context: Context) : Store<RedditData, BarCode> {
        return StoreBuilder.parsedWithKey<BarCode, BufferedSource, RedditData>()
            .fetcher({ provideApi().fetchSubreddit(it.key, "50").map({ it.source()}) })
            .persister(newPersister(context))
            .parser(MoshiParserFactory.createSourceParser(Moshi.Builder().build(), RedditData::class.java))
            .open()
    }

    @Throws(IOException::class)
    private fun newPersister(context: Context): Persister<BufferedSource, BarCode> {
        return SourcePersisterFactory.create(context.cacheDir)
    }

}