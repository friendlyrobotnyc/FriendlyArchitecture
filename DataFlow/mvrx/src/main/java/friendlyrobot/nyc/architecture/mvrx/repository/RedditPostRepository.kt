package friendlyrobot.nyc.architecture.mvrx.repository

import friendlyrobot.nyc.architecture.mvrx.model.RedditPost
import friendlyrobot.nyc.architecture.mvrx.model.toRedditPosts
import friendlyrobot.nyc.architecture.mvrx.network.Post
import friendlyrobot.nyc.architecture.mvrx.network.RedditPostWebservice
import io.reactivex.Single

class RedditPostRepository(
    private val webservice: RedditPostWebservice = RedditPostWebservice()
) {

    fun fetchAwwPosts(): Single<List<RedditPost>> {
        return webservice.fetchPosts("aww", 50) {
            it.isValidAwwPost()
        }.map {
            it.toRedditPosts()
        }
    }

    private fun Post.isValidAwwPost(): Boolean {
        return !(url.startsWith("https://v.redd.it")
                || url.startsWith("https://imgur.com/")
                || url.endsWith("gifv")
                || url.contains("gfycat"))
    }
}