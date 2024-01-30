package com.example.data

import com.example.data.api.Article
import com.example.data.api.NewsApiService
import com.example.data.api.NewsResponse
import com.example.data.api.NewsSource
import com.example.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response
import java.io.IOException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class NewsRepositoryImplTest {

    @Mock
    lateinit var newsApiService: NewsApiService

    lateinit var newsRepository: NewsRepositoryImpl

    fun setup() {
        newsRepository = NewsRepositoryImpl(newsApiService)
    }
    val article = Article(
        source = NewsSource("", ""),
        author = "John Doe",
        title = "Sample Article Title",
        description = "Sample article description",
        url = "https://example.com/article",
        imageUrl = "https://example.com/image.jpg",
        publishedAt = "2024-01-30T12:00:00Z",
        content = "Sample article content" //
    )

    @Test
    fun `test getTopHeadlines success`() {
        runBlocking {
            val response = NewsResponse(status= "OK", totalResults = 1, articles = listOf(article))
            Mockito.`when`(newsApiService.getTopHeadlines(anyString(), anyString())).thenReturn(
                Response.success(response))
            val result = newsRepository.getTopHeadlines("us")
            assertTrue(result.isNotEmpty())
        }
    }

    @Test
    fun `test getTopHeadlines error`() {
        runBlocking {
            Mockito.`when`(newsApiService.getTopHeadlines(anyString(), anyString())).thenReturn(Response.error(404, ResponseBody.create(
                MediaType.parse("text/plain"), "")))
            assertThrows<IOException> {
                newsRepository.getTopHeadlines("us")
            }
        }
    }

    @Test
    fun `test getTopHeadlines exception`() {
        runBlocking {
            Mockito.`when`(newsApiService.getTopHeadlines(anyString(), anyString())).thenThrow(IOException("Network error"))
            assertThrows<IOException> {
                newsRepository.getTopHeadlines("us")
            }
        }
    }
}