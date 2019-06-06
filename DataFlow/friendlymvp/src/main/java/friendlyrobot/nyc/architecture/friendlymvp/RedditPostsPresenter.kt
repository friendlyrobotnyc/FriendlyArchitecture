package friendlyrobot.nyc.architecture.friendlymvp

import androidx.lifecycle.Lifecycle
import com.nytimes.android.external.store3.base.impl.BarCode
import com.nytimes.android.external.store3.base.impl.Store
import io.reactivex.Observable
import io.reactivex.Scheduler
import timber.log.Timber


class RedditPostsPresenter(private val store: Store<RedditData, BarCode>,
                           private val awwRequest: BarCode,
                           private val ioSchedulaer: Scheduler,
                           private val uiScheduler: Scheduler) : BasePresenter<FriendlyRVMVPView>() {

    override fun attachView(view: FriendlyRVMVPView, lifecycle: Lifecycle?) {
        super.attachView(view, lifecycle)

        mvpView?.showLoading()
        getAndShowData()
    }

    private fun getAndShowData() {
        disposables.add(store.get(awwRequest)
            .flatMapObservable { sanitizeData(it) }
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
            .subscribeOn(ioSchedulaer)
            .observeOn(uiScheduler)
            .subscribe({
                    it ->
                Timber.e(it.toString())
                mvpView?.show(it)
            }, {
                Timber.e(it.message, it)
            })
        )
    }

    private fun sanitizeData(redditData: RedditData): Observable<Post> {
        return Observable.fromIterable(redditData?.data?.children).map { it.data }
    }
}