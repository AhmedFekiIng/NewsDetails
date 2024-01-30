package com.example.newsreaderapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject


class NewsViewModel : ViewModel() {

    private val newsRepository: NewsRepository by inject(NewsRepository::class.java)

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val newsListState = MutableLiveData<List<News>>()

    init {
        Log.d("NewsViewModel", "NewsViewModel initialized")
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        Log.d("NewsViewModel", "Fetching top headlines")
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = newsRepository.getTopHeadlines("us")
                newsListState.value = response
            } catch (e: Exception) {
                Log.e("NewsViewModel", "Error fetching top headlines", e)
                _error.value = e.message
            } finally {
                _loading.value = false
                Log.d("NewsViewModel", "Top headlines fetching completed")
            }
        }
    }
}