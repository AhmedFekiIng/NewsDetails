package com.example.data.repository

import com.example.data.api.NewsApiService
import com.example.domain.model.News
import com.example.domain.repository.NewsRepository
import java.io.IOException

class NewsRepositoryImpl(private val newsApi: NewsApiService) : NewsRepository {
    override suspend fun getTopHeadlines(country: String): List<News> {
        return try {
            val response = newsApi.getTopHeadlines(country,"ddd8651a2e9640519be0de205e9cc36b")
            if (response.isSuccessful) {
                response.body()?.articles?.map { article ->
                    News(
                        title = article.title,
                        imageUrl = article.imageUrl ?: "",
                        description = article.description ?: "",
                        articleUrl = article.articleUrl
                    )
                } ?: emptyList()
            } else {
                throw IOException("Error fetching top headlines: ${response.code()}")
            }
        } catch (e: Exception) {
            throw IOException("Error fetching top headlines: ${e.message}", e)
        }
    }
}
