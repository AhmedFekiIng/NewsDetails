package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val title: String,
    val imageUrl: String,
    val description: String,
    val url: String
)