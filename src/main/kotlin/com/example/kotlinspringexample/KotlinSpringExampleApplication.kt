package com.example.kotlinspringexample

import com.example.kotlinspringexample.config.BlogProperties
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class KotlinSpringExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringExampleApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
