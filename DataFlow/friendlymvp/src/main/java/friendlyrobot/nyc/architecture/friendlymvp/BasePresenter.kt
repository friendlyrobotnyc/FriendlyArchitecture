package friendlyrobot.nyc.architecture.friendlymvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable

interface Presenter<in V : MvpView> {

    fun attachView(
        view: V,
        lifecycle: Lifecycle? = null
    )

    fun detachView()
}

open class BasePresenter<T : MvpView> : Presenter<T>, LifecycleObserver {

    protected val disposables = CompositeDisposable()

    var presenterLifecycle: Lifecycle? = null
    var mvpView: T? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(
        view: T,
        lifecycle: Lifecycle?
    ) {
        this.mvpView = view
        presenterLifecycle = lifecycle
        lifecycle?.let { it.addObserver(this) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detachView() {
        presenterLifecycle?.let { it.removeObserver(this) }
        disposables.clear()
        mvpView = null
        presenterLifecycle = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException(
        "Please save Presenter.attachView(MvpView) before" + " requesting data to the Presenter"
    )
}

interface MvpView