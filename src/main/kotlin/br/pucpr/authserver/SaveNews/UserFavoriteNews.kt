package br.pucpr.authserver.SaveNews

import br.pucpr.authserver.news.News
import br.pucpr.authserver.users.User
import jakarta.persistence.*

@Entity
data class UserFavoriteNews(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "news_id")
    val news: News
)