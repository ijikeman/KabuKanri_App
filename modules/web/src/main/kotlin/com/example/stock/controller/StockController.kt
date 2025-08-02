package com.example.stock.controller

import com.example.stock.entity.Owners
import com.example.stock.service.OwnerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class StockController(
    private val ownerService: OwnerService
) {
    @GetMapping("/owners")
    fun ownerList(model: Model): String {
        val owners = ownerService.findAll()
        model.addAttribute("owners", owners)
        return "owners"
    }

    @PostMapping("/owners")
    fun ownerRegister(@RequestParam name: String): String {
        val owner = Owners(name = name)
        ownerService.save(owner)
        // 保存後、一覧画面にリダイレクトします。
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
