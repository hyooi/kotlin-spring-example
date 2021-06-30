package com.example.kotlinspringexample.api

import com.example.kotlinspringexample.entity.Article
import com.example.kotlinspringexample.entity.User
import com.example.kotlinspringexample.extension.format
import com.example.kotlinspringexample.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HomeController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "blog"
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }

        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository.findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article

        return "article"
    }

private fun Article.render() = RenderedArticle(
    slug, title, headline, content, author, addedAt.format()
)

data class RenderedArticle(
    val slug: String,
    val title: String,
    val headline: String,
    val content: String,
    val author: User,
    val addedAt: String)
}
