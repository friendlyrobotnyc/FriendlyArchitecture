package friendlyrobot.nyc.architecture.mvrx.viewmodel

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import friendlyrobot.nyc.architecture.mvrx.model.RedditPost

data class RedditPostState(
    val posts: Async<List<RedditPost>> = Uninitialized
) : MvRxState