package br.pucpr.authserver.news.responses

import br.pucpr.authserver.news.News
import java.time.LocalDateTime

data class NewsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val imageUrl: String,
    val createdAt: LocalDateTime
) {
    constructor(news: News) : this(
        id = news.id,
        title = news.title,
        content = news.content,
        imageUrl = news.imageUrl,
        createdAt = news.createdAt
    )
}