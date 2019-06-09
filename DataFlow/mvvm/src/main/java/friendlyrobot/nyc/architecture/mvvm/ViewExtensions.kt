package friendlyrobot.nyc.architecture.mvvm

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

fun View.lifecycle() = (activity() as LifecycleOwner).lifecycle

fun View.activity(): AppCompatActivity {
    var viewContext = context
    while (viewContext is ContextWrapper) {
        if (viewContext is AppCompatActivity) {
            return viewContext
        }
        viewContext = (context as ContextWrapper).baseContext
    }
    throw IllegalStateException(
        "Context does not stem from an activity: " + java.security.AccessController.getContext()
    )
}