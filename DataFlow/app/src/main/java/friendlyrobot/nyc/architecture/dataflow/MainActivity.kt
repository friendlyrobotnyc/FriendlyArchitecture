package friendlyrobot.nyc.architecture.dataflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import friendlyrobot.nyc.architecture.dataflow.adapters.DataFlowAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ViewPager2>(R.id.viewPager).run {
            adapter = DataFlowAdapter()
        }
    }
}

