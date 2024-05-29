package br.pucpr.authserver.SaveNews

import br.pucpr.authserver.news.NewsService
import br.pucpr.authserver.users.UserService
import org.springframework.stereotype.Service

@Service
class UserFavoriteNewsService(
    val userFavoriteNewsRepository: UserFavoriteNewsRepository,
    val userService: UserService,
    val newsService: NewsService
) {
    fun saveFavoriteNews(userId: Long, newsId: Long): UserFavoriteNews {
        val user = userService.findByIdOrNull(userId) ?: throw RuntimeException("User not found")
        val news = newsService.findById(newsId) ?: throw RuntimeException("News not found")
        val favoriteNews = UserFavoriteNews(user = user, news = news)
        return userFavoriteNewsRepository.save(favoriteNews)
    }

    fun getFavoriteNewsByUserId(userId: Long): List<UserFavoriteNews> {
        return userFavoriteNewsRepository.findByUserId(userId)
    }

    fun deleteFavoriteNews(userId: Long, newsId: Long) {
        val favoriteNews = userFavoriteNewsRepository.findByUserId(userId)
            .firstOrNull { it.news.id == newsId }
            ?: throw RuntimeException("Favorite news not found")
        userFavoriteNewsRepository.delete(favoriteNews)
    }
}