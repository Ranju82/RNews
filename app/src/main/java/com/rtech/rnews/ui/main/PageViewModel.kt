package com.rtech.rnews.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rtech.rnews.network.Article
import com.rtech.rnews.network.NewsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class NewsApiStatus { LOADING, ERROR, DONE }

class PageViewModel : ViewModel() {

    private var _articleList=MutableLiveData<List<Article>>()
    val articleList:LiveData<List<Article>>
        get() = _articleList

    private val _status = MutableLiveData<NewsApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<NewsApiStatus>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    fun setIndex(index: Int) {
        when(index){
            1->getHeadlinesNews("general")
            2->getHeadlinesNews("business")
            3->getHeadlinesNews("technology")
            4->getHeadlinesNews("sports")
        }
    }

    private fun getHeadlinesNews(category:String){
        coroutineScope.launch {
            var getDeferredHeadlines=NewsApi.retrofitService.getHeadlines(category,"en")
            try {
                _status.value=NewsApiStatus.LOADING
                val res=getDeferredHeadlines.await()
                _status.value=NewsApiStatus.DONE
                _articleList.value=res.articles
            }catch (e:Exception){
                _status.value=NewsApiStatus.DONE
                _articleList.value=ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}