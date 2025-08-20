package com.example.stock.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TopController {

    @GetMapping("/")
    fun top(): String {
        return "top"
    }
}
