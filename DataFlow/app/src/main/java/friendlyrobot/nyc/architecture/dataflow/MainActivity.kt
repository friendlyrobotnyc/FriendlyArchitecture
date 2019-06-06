package friendlyrobot.nyc.architecture.dataflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val flows = arrayOf<DataFlow>(DataFlow(R.layout.view_friendly_layout, "FriendlyMVP"),
            DataFlow(R.layout.view_mvvm_layout, "MVVM"))

        viewPager.adapter = DataFlowAdapter(flows)
    }

}

data class DataFlow(
    val resid: Int,
    val label: String
)

class DataFlowAdapter(private val flows : Array<DataFlow>) : RecyclerView.Adapter<DataFlowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataFlowViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(flows[viewType].resid, parent, false)
        return DataFlowViewHolder(inflatedView)

    }

    override fun getItemCount(): Int {
        return flows.size
    }

    override fun onBindViewHolder(holder: DataFlowViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}


class DataFlowViewHolder internal constructor(private val dataFlowView: View) :
        RecyclerView.ViewHolder(dataFlowView) {
    internal fun bind() {
        //do nthing currently
    }
}
