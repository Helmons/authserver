package br.pucpr.authserver

import br.pucpr.authserver.SaveNews.UserFavoriteNews
import br.pucpr.authserver.SaveNews.UserFavoriteNewsRepository
import br.pucpr.authserver.SaveNews.UserFavoriteNewsService
import br.pucpr.authserver.news.News
import br.pucpr.authserver.news.NewsService
import br.pucpr.authserver.users.User
import br.pucpr.authserver.users.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserFavoriteNewsServiceTest {

    @Mock
    private lateinit var userFavoriteNewsRepository: UserFavoriteNewsRepository

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var newsService: NewsService

    @InjectMocks
    private lateinit var userFavoriteNewsService: UserFavoriteNewsService

    @Test
    fun `test add favorite news`() {
        val user = User(id = 1L, email = "test@example.com", password = "password", name = "Test User")
        val news = News(id = 1L, title = "Test Title", content = "Test Content", imageUrl = "http://example.com/image.jpg")
        val favoriteNews = UserFavoriteNews(user = user, news = news)

        Mockito.`when`(userService.findByIdOrNull(1L)).thenReturn(user)
        Mockito.`when`(newsService.findById(1L)).thenReturn(news)
        Mockito.`when`(userFavoriteNewsRepository.save(favoriteNews)).thenReturn(favoriteNews)

        val result = userFavoriteNewsService.saveFavoriteNews(1L, 1L)
        assertNotNull(result)
        assertEquals(user, result.user)
        assertEquals(news, result.news)
    }

    @Test
    fun `test get favorite news by user id`() {
        val user = User(id = 1L, email = "test@example.com", password = "password", name = "Test User")
        val news = News(id = 1L, title = "Test Title", content = "Test Content", imageUrl = "http://example.com/image.jpg")
        val favoriteNews = UserFavoriteNews(user = user, news = news)
        val favoriteNewsList = listOf(favoriteNews)

        Mockito.`when`(userFavoriteNewsRepository.findByUserId(1L)).thenReturn(favoriteNewsList)

        val result = userFavoriteNewsService.getFavoriteNewsByUserId(1L)
        assertEquals(1, result.size)
        assertEquals(news, result[0].news)
    }
}