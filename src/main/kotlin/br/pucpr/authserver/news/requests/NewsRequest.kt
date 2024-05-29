package br.pucpr.authserver.news.requests

data class NewsRequest(
    val title: String,
    val content: String,
    val imageUrl: String
)