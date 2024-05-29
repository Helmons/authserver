package br.pucpr.authserver.news

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository : JpaRepository<News, Long> {
    fun findByTitleContainingIgnoreCase(title: String): List<News>
}