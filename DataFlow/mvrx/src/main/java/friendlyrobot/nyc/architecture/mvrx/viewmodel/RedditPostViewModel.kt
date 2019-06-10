package friendlyrobot.nyc.architecture.mvrx.viewmodel

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import friendlyrobot.nyc.architecture.mvrx.repository.RedditPostRepository
import io.reactivex.schedulers.Schedulers

class RedditPostViewModel(
    initialState: RedditPostState,
    repository: RedditPostRepository
) : MvRxViewModel<RedditPostState>(initialState) {

    init {
        repository.fetchAwwPosts()
            .subscribeOn(Schedulers.io())
            .execute { copy(posts = it) }
    }

    companion object : MvRxViewModelFactory<RedditPostViewModel, RedditPostState> {
        override fun create(viewModelContext: ViewModelContext, state: RedditPostState): RedditPostViewModel? {
            return RedditPostViewModel(state, RedditPostRepository())
        }
    }
}