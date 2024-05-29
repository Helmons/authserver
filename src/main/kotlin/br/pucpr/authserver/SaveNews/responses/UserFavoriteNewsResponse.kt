package br.pucpr.authserver.SaveNews.responses

import br.pucpr.authserver.SaveNews.UserFavoriteNews

data class UserFavoriteNewsResponse(
    val id: Long,
    val userId: Long,
    val newsId: Long,
    val newsTitle: String,
    val newsContent: String,
    val newsImageUrl: String
) {
    constructor(favoriteNews: UserFavoriteNews) : this(
        id = favoriteNews.id!!,
        userId = favoriteNews.user.id!!,
        newsId = favoriteNews.news.id,
        newsTitle = favoriteNews.news.title,
        newsContent = favoriteNews.news.content,
        newsImageUrl = favoriteNews.news.imageUrl
    )
}