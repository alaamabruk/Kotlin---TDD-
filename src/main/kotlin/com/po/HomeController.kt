package com.po

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HomeController {

    @GetMapping("/hi")
    fun Hello() : String = "Hi from Kotlin and mac"
}