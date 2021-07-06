package com.example.kotlinspringexample

import com.example.kotlinspringexample.config.BlogProperties
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import java.util.function.Function

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
@EnableWebSecurity
class KotlinSpringExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringExampleApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
        addInitializers(beans {
            bean {
                fun user(user: String, password: String, vararg roles: String) =
                    User.withUsername(user)
                        .password("{noop}$password")
                        .roles(*roles)
                        .build()

                InMemoryUserDetailsManager(user("user", "password", "USER"),
                user("admin", "password", "USER", "ADMIN"))
            }

            bean {
                router {
                    GET("/greetings") {
                        request -> request.principal().map { it.name }
                        .map { ServerResponse.ok().body(mapOf("greeting" to "Hello $it")) }
                        .orElseGet {ServerResponse.badRequest().build()}
                    }
                }
            }
        })
    }
}
