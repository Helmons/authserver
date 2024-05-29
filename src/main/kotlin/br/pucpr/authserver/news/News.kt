package br.pucpr.authserver.news

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class News(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    @Column(columnDefinition = "TEXT")
    val content: String,
    @Column(columnDefinition = "TEXT")
    val imageUrl: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)