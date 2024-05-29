package br.pucpr.authserver
import br.pucpr.authserver.news.News
import br.pucpr.authserver.news.NewsRepository
import br.pucpr.authserver.news.NewsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class NewsServiceTest {

    @Mock
    private lateinit var newsRepository: NewsRepository

    @InjectMocks
    private lateinit var newsService: NewsService

    @Test
    fun `test save news`() {
        val news = News(title = "Test Title", content = "Test Content", imageUrl = "http://example.com/image.jpg")
        Mockito.`when`(newsRepository.save(news)).thenReturn(news)

        val savedNews = newsService.save(news)
        assertNotNull(savedNews)
        assertEquals("Test Title", savedNews.title)
    }

    @Test
    fun `test find all news`() {
        val newsList = listOf(
            News(title = "Test Title 1", content = "Test Content 1", imageUrl = "http://example.com/image1.jpg"),
            News(title = "Test Title 2", content = "Test Content 2", imageUrl = "http://example.com/image2.jpg")
        )
        Mockito.`when`(newsRepository.findAll()).thenReturn(newsList)

        val result = newsService.findAll()
        assertEquals(2, result.size)
    }

    @Test
    fun `test find news by id`() {
        val news = News(id = 1L, title = "Test Title", content = "Test Content", imageUrl = "http://example.com/image.jpg")
        Mockito.`when`(newsRepository.findById(1L)).thenReturn(java.util.Optional.of(news))

        val result = newsService.findById(1L)
        assertNotNull(result)
        assertEquals("Test Title", result?.title)
    }
}