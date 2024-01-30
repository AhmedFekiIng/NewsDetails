package com.example.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("source") val source: NewsSource,
    @SerialName("author") val author: String?,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String?,
    @SerialName("url") val url: String,
    @SerialName("urlToImage") val imageUrl: String?,
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("content") val content: String?
)

data class NewsSource(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String
)

