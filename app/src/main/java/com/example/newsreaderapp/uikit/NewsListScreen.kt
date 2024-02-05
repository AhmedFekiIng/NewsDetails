package com.example.newsreaderapp.uikit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.domain.model.News
import com.example.newsreaderapp.viewmodel.NewsViewModel
@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsViewModel = viewModel()) {
    val newsListState by viewModel.newsListState.observeAsState()
    val loading by viewModel.loading.observeAsState(initial = false)
    val error by viewModel.error.observeAsState(initial = "")
    val selectedNews = remember { mutableStateOf<News?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchTopHeadlines()
    }

    if (loading) {
        CircularProgressIndicator()
    } else if (error.isNotEmpty()) {
        Text(text = error)
    } else {
        val newsList = newsListState ?: emptyList()
        LazyColumn {
            itemsIndexed(newsList) { index, news ->
                Text(
                    text = news.title,
                    modifier = Modifier.clickable {
                        selectedNews.value = News(news.title, news.description, news.imageUrl,news.url)
                        navController.navigate("newsDetailScreen/$index")
                    }
                )
            }
        }

    }
}