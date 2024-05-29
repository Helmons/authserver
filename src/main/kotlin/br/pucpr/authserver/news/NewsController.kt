package br.pucpr.authserver.news

import br.pucpr.authserver.news.requests.NewsRequest
import br.pucpr.authserver.news.responses.NewsResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/news")
class NewsController(
    val newsService: NewsService
) {
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "WebToken")
    fun insert(@RequestBody @Valid newsRequest: NewsRequest): ResponseEntity<NewsResponse> {
        val news = newsService.save(
            News(
                title = newsRequest.title,
                content = newsRequest.content,
                imageUrl = newsRequest.imageUrl
            )
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(NewsResponse(news))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<NewsResponse>> {
        val newsList = newsService.findAll().map { NewsResponse(it) }
        return ResponseEntity.ok(newsList)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<NewsResponse> {
        val news = newsService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(NewsResponse(news))
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "WebToken")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        newsService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/search")
    fun search(@RequestParam title: String): ResponseEntity<List<NewsResponse>> {
        val newsList = newsService.searchByTitle(title).map { NewsResponse(it) }
        return ResponseEntity.ok(newsList)
    }
}
