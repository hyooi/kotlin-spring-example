package com.example.kotlinspringexample.repository

import com.example.kotlinspringexample.entity.Article
import com.example.kotlinspringexample.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTest @Autowired constructor(
    private val entityManager: TestEntityManager,
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository
) {

    @Test
    fun `findByIdOrNull then return article`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        entityManager.persist(juergen)

        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community...", "Lorem " +
                "ipsum", juergen)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `findByLogin then return User`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        entityManager.persist(juergen)
        entityManager.flush()

        val user = userRepository.findByLogin(juergen.login)
        assertThat(user).isEqualTo(juergen)
    }
}