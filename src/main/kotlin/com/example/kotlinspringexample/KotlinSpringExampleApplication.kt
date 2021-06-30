package com.example.kotlinspringexample

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringExampleApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
