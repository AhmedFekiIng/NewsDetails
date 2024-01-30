package com.example.domain.repository

import com.example.domain.model.News

interface NewsRepository {
    suspend fun getTopHeadlines(country: String): List<News>
}
