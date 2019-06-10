package friendlyrobot.nyc.architecture.mvrx.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditPostApi {

    @GET("r/{subredditName}/new/.json")
    fun fetchSubreddit(
        @Path("subredditName") subredditName: String,
        @Query("limit") limit: String
    ): Observable<RedditData>
}