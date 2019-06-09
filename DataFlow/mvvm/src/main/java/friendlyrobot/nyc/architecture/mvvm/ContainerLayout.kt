package friendlyrobot.nyc.architecture.mvvm

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_reddit_post.view.*
import timber.log.Timber

class ContainerLayout @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    val posts: PostsViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var progressbar: ProgressBar
    private val friendlyAdapter = FriendlyAdapter()

    init {
        posts = PostsViewModel.create(activity(), AppModule.provideMvvmApi())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        recyclerView = findViewById(R.id.recyclerview)
        progressbar = findViewById(R.id.indeterminateBar)

        configureRecyclerView()
        showLoading()

        posts.mydata.observe(activity(), Observer { resp ->

            Timber.e("foo $resp")
            Observable.just(resp)
                .flatMap { sanitizeData(it) }
                .filter {
                    var emit = true
                    if (it.url.startsWith("https://v.redd.it")
                        || it.url.startsWith("https://imgur.com/")
                        || it.url.endsWith("gifv")
                        || it.url.contains("gfycat")) {
                        emit = false
                    }
                    emit
                }
                .toList()
                .subscribe({
                    showPosts(it)
                },{
                    Timber.e(it.message, it)
                })
        })
    }

    fun showPosts(posts: List<Post>) {
        friendlyAdapter.items.clear()
        friendlyAdapter.items.addAll(posts)
        friendlyAdapter.notifyDataSetChanged()
        progressbar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    fun showLoading() {
        recyclerView.visibility = View.GONE
        progressbar.visibility = View.VISIBLE
    }

    private fun sanitizeData(redditData: RedditData): Observable<Post> {
        return Observable.fromIterable(redditData?.data?.children).map { it.data }
    }

    private fun configureRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = friendlyAdapter
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

    var items: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        return PostVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reddit_post, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }
}