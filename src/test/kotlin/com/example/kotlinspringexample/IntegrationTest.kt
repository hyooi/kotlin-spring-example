package com.example.kotlinspringexample

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IntegrationTest (@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setUp() { //static을 사용하지 않으려면 클래스별로 라이프사이클 변경 필요
        println(">> Setup")
    }

    @Test
    fun `blog첫페이지 요청`() {
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>blog</h1>")
        assertThat(entity.body).contains("Hello World!")
    }

    @AfterAll
    fun tearDown() {
        println(">> Tear down")
    }
}