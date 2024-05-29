package br.pucpr.authserver.SaveNews

import org.springframework.data.jpa.repository.JpaRepository

interface UserFavoriteNewsRepository : JpaRepository<UserFavoriteNews, Long> {
    fun findByUserId(userId: Long): List<UserFavoriteNews>
}