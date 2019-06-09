package friendlyrobot.nyc.architecture.mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MvvmApi {

    @GET("r/{subredditName}/new/.json")
    fun fetchSubreddit(
        @Path("subredditName") subredditName: String,
        @Query("limit") limit: String
    ): Call<RedditData>

}