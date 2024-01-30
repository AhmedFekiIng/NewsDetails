package com.example.newsreaderapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.News
import com.example.newsreaderapp.uikit.NewsDetailScreen
import com.example.newsreaderapp.uikit.NewsListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "newsListScreen") {
        composable("newsListScreen") {
            NewsListScreen(navController = navController)
        }
        composable("newsDetailScreen") {
            val newsDetail = it.arguments?.getSerializable("newsDetail") as? News
            if (newsDetail != null) {
                NewsDetailScreen(newsDetail)
            }
        }
    }
}