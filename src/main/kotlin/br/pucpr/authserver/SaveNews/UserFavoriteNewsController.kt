package br.pucpr.authserver.SaveNews

import br.pucpr.authserver.SaveNews.requests.UserFavoriteNewsRequest
import br.pucpr.authserver.SaveNews.responses.UserFavoriteNewsResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user-favorite-news")
class UserFavoriteNewsController(
    val userFavoriteNewsService: UserFavoriteNewsService
) {
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "WebToken")
    fun saveFavoriteNews(@RequestBody userFavoriteNewsRequest: UserFavoriteNewsRequest): ResponseEntity<UserFavoriteNewsResponse> {
        val favoriteNews = userFavoriteNewsService.saveFavoriteNews(
            userFavoriteNewsRequest.userId,
            userFavoriteNewsRequest.newsId
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(UserFavoriteNewsResponse(favoriteNews))
    }

    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    fun getFavoriteNews(@PathVariable userId: Long): ResponseEntity<List<UserFavoriteNewsResponse>> {
        val favoriteNewsList = userFavoriteNewsService.getFavoriteNewsByUserId(userId).map { UserFavoriteNewsResponse(it) }
        return ResponseEntity.ok(favoriteNewsList)
    }

    @DeleteMapping("/{userId}/{newsId}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "WebToken")
    fun deleteFavoriteNews(@PathVariable userId: Long, @PathVariable newsId: Long): ResponseEntity<Void> {
        userFavoriteNewsService.deleteFavoriteNews(userId, newsId)
        return ResponseEntity.noContent().build()
    }
}