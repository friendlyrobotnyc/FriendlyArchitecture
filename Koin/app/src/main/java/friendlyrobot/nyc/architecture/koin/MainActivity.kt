package friendlyrobot.nyc.architecture.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope

class MainActivity : AppCompatActivity() {

    val firstPresenter: MySimplePresenter by inject()
    val scopedPresenter: ScopedPresenter by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Koin:", firstPresenter.sayHello())
        Log.e("Koin scoped:", scopedPresenter.sayHello())
    }
}
