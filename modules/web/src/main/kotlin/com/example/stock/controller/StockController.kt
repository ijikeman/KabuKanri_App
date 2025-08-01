package com.example.stock.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.stereotype.Controller

@Controller
class StockController {
    @GetMapping("/owners")
    fun ownerList(): String {
        return "owners"
    }

    @PostMapping("/owners")
    fun ownerRegister(): String {
        return "redirect:/owners"
    }

    @GetMapping("/brokers")
    fun brokerList(): String {
        return "brokers"
    }

    @PostMapping("/brokers")
    fun brokerRegister(): String {
        return "redirect:/brokers"
    }

    @GetMapping("/sectors")
    fun sectorList(): String {
        return "sectors"
    }

    @PostMapping("/sectors")
    fun sectorRegister(): String {
        return "redirect:/sectors"
    }
}
