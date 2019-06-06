package friendlyrobot.nyc.architecture.friendlymvp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nytimes.android.external.store3.base.impl.BarCode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_reddit_post.view.*


interface FriendlyRVMVPView : MvpView {
    fun show(posts: List<Post>)
    fun showLoading()
}

class RedditPosts @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle), FriendlyRVMVPView {

    lateinit var redditPostsPresenter: RedditPostsPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var progressbar: ProgressBar
    private val friendlyAdapter = FriendlyAdapter()

    override fun onFinishInflate() {
        super.onFinishInflate()
        recyclerView = findViewById(R.id.recyclerview)
        progressbar = findViewById(R.id.indeterminateBar)

        configureRecyclerView()

        //would be done via an inject
        redditPostsPresenter = RedditPostsPresenter(AppModule.provideStore(context),
            BarCode(RedditData::class.java.simpleName, "aww"),
            Schedulers.io(),
            AndroidSchedulers.mainThread())
    }

    private fun configureRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = friendlyAdapter
    }

    override fun show(posts: List<Post>) {
        friendlyAdapter.items.clear()
        friendlyAdapter.items.addAll(posts)
        friendlyAdapter.notifyDataSetChanged()
        progressbar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showLoading() {
        recyclerView.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        redditPostsPresenter.attachView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        redditPostsPresenter.detachView()
    }
}

class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post) {
        Glide.with(itemView.context).load(post.url).into(itemView.post_image)
        itemView.post_title.text = post.title
    }
}

class FriendlyAdapter : RecyclerView.Adapter<PostVH>() {

    override fun onBindViewHolder(holder: PostVH, position: Int) {
        items.get(position).let { holder.bind(it) }
    }

    var items:MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        return PostVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reddit_post, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}