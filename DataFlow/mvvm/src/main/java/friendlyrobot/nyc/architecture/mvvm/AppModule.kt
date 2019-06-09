package friendlyrobot.nyc.architecture.mvvm

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {

    fun provideMvvmApi() : MvvmApi {
        return Retrofit.Builder()
            .baseUrl("https://reddit.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .validateEagerly(BuildConfig.DEBUG)  // Fail early: check Retrofit configuration at creation time in Debug build.
            .build()
            .create(MvvmApi::class.java)
    }
}