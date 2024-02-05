package com.example.data.repository

import android.content.Context
import android.util.Log
import com.example.data.api.NewsApiService
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import java.io.IOException
import java.util.Properties

class NewsRepositoryImpl(private val context: Context, private val newsApi: NewsApiService) : NewsRepository {
    private val apiKey: String
    init {
        val properties = Properties().apply {
            load(context.assets.open("api.properties"))
        }
        apiKey = properties.getProperty("api_key")
    }

    override suspend fun getTopHeadlines(country: String): List<News> {
        return try {
            val response = newsApi.getTopHeadlines(country,apiKey)
            if (response.isSuccessful) {
                response.body()?.articles?.map { article ->
                    News(
                        title = article.title,
                        imageUrl = article.imageUrl ?: "",
                        description = article.description ?: "",
                        url = article.url
                    )
                } ?: emptyList()
            } else {
                throw IOException("Error fetching top headlines: ${response.code()}")
            }
        } catch (e: Exception) {
            throw IOException("Error fetching top headlines: ${e.message}", e)
        }
    }

    override suspend fun getNews(country: String, item: Int): News? {
        return try {
            val allNews = getTopHeadlines(country)
            if (item in allNews.indices) {
                return allNews[item]
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("DeviceRepositoryImpl", "Error fetching device: ${e.message}")
            null
        }
    }
}
