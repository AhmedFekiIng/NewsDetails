package com.example.newsreaderapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.News
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
        composable("newsDetailScreen/{news}") { backStackEntry ->
            val newsJson = backStackEntry.arguments?.getString("news")
            val news = newsJson?.let { json ->
                try {
                    Json.decodeFromString<News>(json)
                } catch (e: Exception) {
                    null
                }
            }
            if (news != null) {
                NewsDetailScreen(news = news)
            } else {
                Text(text = "Une erreur est survenue : Détails de la news non trouvés.")
            }
        }
    }
}