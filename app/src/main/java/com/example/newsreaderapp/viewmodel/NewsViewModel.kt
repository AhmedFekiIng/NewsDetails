package com.example.newsreaderapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val newsListState = MutableLiveData<List<News>>()

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val news = newsRepository.getTopHeadlines("us")
                newsListState.value = news
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}