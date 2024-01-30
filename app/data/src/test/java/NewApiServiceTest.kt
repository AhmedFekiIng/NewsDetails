import com.example.data.api.Article
import com.example.data.api.NewsApiService
import com.example.data.api.NewsResponse
import com.example.data.api.NewsSource
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

class NewsApiServiceTest {

    @Mock
    lateinit var newsApiService: NewsApiService

    @Test
    fun `test getTopHeadlines success`() {
        // Mock successful response
        runBlocking {
            val response = NewsResponse(status= "OK", totalResults = 1, articles = listOf(article))
            Mockito.`when`(newsApiService.getTopHeadlines(anyString(), anyString())).thenReturn(Response.success(response))
            val result = newsApiService.getTopHeadlines("us", "test_api_key")
            assertNotNull(result.body())
            assertTrue(result.isSuccessful)
        }
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
    fun `test getTopHeadlines empty response`() {
        // Mock empty response
        runBlocking {
            val response = NewsResponse(status= "OK", totalResults = 1, articles = listOf(article))
            Mockito.`when`(newsApiService.getTopHeadlines(anyString(), anyString())).thenReturn(
                Response.success(response))
            val result = newsApiService.getTopHeadlines("us", "test_api_key")
            assertNotNull(result.body())
            assertTrue(result.isSuccessful)
            assertTrue(result.body()?.articles?.isEmpty() ?: false)
        }
    }

}