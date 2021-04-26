package com.example.kotlinspringexample.api

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "blog"
        model["content"] = "Hello World!"
        return "blog"
    }
}