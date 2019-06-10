package friendlyrobot.nyc.architecture.mvrx

import friendlyrobot.nyc.architecture.mvrx.network.RedditPostApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {

    val redditPostApi: RedditPostApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .validateEagerly(BuildConfig.DEBUG)  // Fail early: check Retrofit configuration at creation time in Debug build.
            .build()
            .create(RedditPostApi::class.java)
    }
}