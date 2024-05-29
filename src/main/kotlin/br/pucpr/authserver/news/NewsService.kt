package br.pucpr.authserver.news

import org.springframework.stereotype.Service

@Service
class NewsService(
    val newsRepository: NewsRepository
) {
    fun save(news: News): News = newsRepository.save(news)
    fun findAll(): List<News> = newsRepository.findAll()
    fun findById(id: Long): News? = newsRepository.findById(id).orElse(null)
    fun delete(id: Long) = newsRepository.deleteById(id)
    fun searchByTitle(title: String): List<News> = newsRepository.findByTitleContainingIgnoreCase(title)
}