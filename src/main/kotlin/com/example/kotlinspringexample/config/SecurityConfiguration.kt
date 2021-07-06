package com.example.kotlinspringexample.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Order(1) //BasicSecurityConfiguration보다 AdminSecurityConfiguration이 먼저 적용됨
@Configuration
class AdminSecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/greetings/**").hasAuthority("ROLE_ADMIN")
    }
}

@Configuration
class BasicSecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.httpBasic()
            .and()
            .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/**").permitAll()
            .antMatchers(HttpMethod.GET, "/**").hasAuthority("ROLE_ADMIN")
    }
}
