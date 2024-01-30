package com.example.newsreaderapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newsreaderapp.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(navController: NavController) {
    val newsViewModel: NewsViewModel = viewModel()
    val newsListState by newsViewModel.newsListState.observeAsState()
    val loading by newsViewModel.loading.observeAsState(initial = false)
    val error by newsViewModel.error.observeAsState(initial = "")

    if (loading) {
        CircularProgressIndicator()
    } else if (error.isNotEmpty()) {
        Text(text = error)
    } else {
        val newsList = newsListState ?: emptyList()
        LazyColumn {
            items(newsList) { news ->
                Text(
                    text = news.title,
                    modifier = Modifier.clickable {
                        navController.navigate("newsDetailScreen/${news.title}")
                    }
                )
            }
        }
    }
}