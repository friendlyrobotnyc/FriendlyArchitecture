package friendlyrobot.nyc.architecture.mvrx.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.*
import friendlyrobot.nyc.architecture.mvrx.R
import friendlyrobot.nyc.architecture.mvrx.ui.view.redditPostRow
import friendlyrobot.nyc.architecture.mvrx.viewmodel.RedditPostViewModel

class RedditPostsFragment : BaseMvRxFragment() {

    private lateinit var loadingView: View
    private lateinit var redditPostsView: EpoxyRecyclerView
    private lateinit var errorView: View

    private val viewModel: RedditPostViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reddit_posts, container, false)
        loadingView = view.findViewById(R.id.fragment_posts_progress)
        redditPostsView = view.findViewById(R.id.fragment_posts_recyclerview)
        errorView = view.findViewById(R.id.fragment_posts_error)
        return view
    }

    override fun invalidate() = withState(viewModel) { state ->
        loadingView.isVisible = state.posts is Loading
        errorView.isVisible = state.posts is Fail
        redditPostsView.isVisible = state.posts is Uninitialized || state.posts is Success
        redditPostsView.withModels {
            state.posts()?.forEach { post ->
                redditPostRow {
                    id(post.imageUrl)
                    redditPost(post)
                    clickListener { _ ->
                        toast("Clicked post ${post.title}")
                    }
                }
            }
        }
    }

    private fun Fragment.toast(msg: String) {
        context?.let {
            Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
        }
    }
}