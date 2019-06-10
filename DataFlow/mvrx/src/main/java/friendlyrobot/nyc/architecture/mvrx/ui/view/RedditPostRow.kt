package friendlyrobot.nyc.architecture.mvrx.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import friendlyrobot.nyc.architecture.mvrx.R
import friendlyrobot.nyc.architecture.mvrx.model.RedditPost

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class RedditPostRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val titleView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.row_reddit_post, this, true)
        imageView = findViewById(R.id.post_image)
        titleView = findViewById(R.id.post_title)
    }

    @ModelProp
    fun setRedditPost(post: RedditPost) = with(post) {
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)
        titleView.text = title
    }

    @CallbackProp
    fun setClickListener(listener: OnClickListener?) {
        setOnClickListener(listener)
    }
}