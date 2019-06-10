package friendlyrobot.nyc.architecture.dataflow.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import friendlyrobot.nyc.architecture.dataflow.R

class DataFlowAdapter(
    private val flows: Array<DataFlow> = arrayOf(
        DataFlow(R.layout.view_friendly_layout, "FriendlyMVP"),
        DataFlow(R.layout.view_mvvm_layout, "MVVM"),
        DataFlow(R.layout.view_mvrx_layout, "MvRx")
    )
) : RecyclerView.Adapter<DataFlowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataFlowViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(flows[viewType].resid, parent, false)
        return DataFlowViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = flows.size

    override fun onBindViewHolder(holder: DataFlowViewHolder, position: Int) = Unit

    override fun getItemViewType(position: Int): Int = position
}

class DataFlowViewHolder internal constructor(
    dataFlowView: View
) : RecyclerView.ViewHolder(dataFlowView) {

    //do nthing currently
    internal fun bind() = Unit
}

data class DataFlow(
    val resid: Int,
    val label: String
)
