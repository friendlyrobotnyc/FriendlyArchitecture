package friendlyrobot.nyc.architecture.mvvm

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel(private val mvvmApi: MvvmApi) : ViewModel() {

    val mydata: MutableLiveData<RedditData>

    init {
        mydata = getRedditData()
    }

    private fun getRedditData(): MutableLiveData<RedditData> {

        val mydata = MutableLiveData<RedditData>()

        mvvmApi.fetchSubreddit("aww", "50").enqueue(object : Callback<RedditData> {
            override fun onFailure(call: Call<RedditData>, t: Throwable) {
                mydata.value = null
            }

            override fun onResponse(call: Call<RedditData>, response: Response<RedditData>) {
                if (response.isSuccessful) {
                    mydata.value = response.body()
                }
            }
        })
        return mydata
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val mvvmApi: MvvmApi) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PostsViewModel(mvvmApi) as T
        }
    }

    companion object {
        @JvmStatic
        fun create(activity: FragmentActivity, mvvmApi: MvvmApi): PostsViewModel {
            return ViewModelProviders.of(activity, Factory(mvvmApi)).get(PostsViewModel::class.java)
        }
    }

}

