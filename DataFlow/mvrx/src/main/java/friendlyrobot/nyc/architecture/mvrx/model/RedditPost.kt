package friendlyrobot.nyc.architecture.mvrx.model

import friendlyrobot.nyc.architecture.mvrx.network.Post

data class RedditPost(val title: String, val imageUrl: String)

fun List<Post>.toRedditPosts(): List<RedditPost> = map {
    RedditPost(
        it.title,
        it.url
    )
}