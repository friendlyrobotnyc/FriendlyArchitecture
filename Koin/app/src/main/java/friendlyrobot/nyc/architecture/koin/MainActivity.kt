package friendlyrobot.nyc.architecture.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val firstPresenter: MySimplePresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Foo:" + firstPresenter.sayHello(), "foo me")
    }
}
