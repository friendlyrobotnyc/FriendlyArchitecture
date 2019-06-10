package friendlyrobot.nyc.architecture.mvrx.network

import friendlyrobot.nyc.architecture.mvrx.AppModule
import io.reactivex.Observable
import io.reactivex.Single

class RedditPostWebservice(private val api: RedditPostApi = AppModule.redditPostApi) {

    /**
     * Fetch reddit posts from the specified subreddit. Default fetch size is 50.
     * Optionally pass a validator to filter posts.
     */
    fun fetchPosts(subredditName: String, count: Int = 50, validator: (Post) -> Boolean): Single<List<Post>> {
        return api.fetchSubreddit(subredditName, count.toString())
            .flatMap { sanitizeData(it) }
            .filter(validator)
            .toList()
    }

    private fun sanitizeData(redditData: RedditData): Observable<Post> {
        return Observable.fromIterable(redditData.data.children.map { it.data })
    }
}