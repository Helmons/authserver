package br.pucpr.authserver.SaveNews.requests


data class UserFavoriteNewsRequest(
    val userId: Long,
    val newsId: Long
)