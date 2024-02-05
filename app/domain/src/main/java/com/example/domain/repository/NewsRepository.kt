package com.example.domain.repository

import com.example.domain.model.News

interface NewsRepository {
    suspend fun getTopHeadlines(country: String): List<News>
    suspend fun getNews(country: String,item: Int): News?

}
