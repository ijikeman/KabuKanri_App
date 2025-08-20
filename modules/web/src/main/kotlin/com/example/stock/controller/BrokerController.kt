package com.example.stock.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class BrokerController {
    @GetMapping("/brokers")
    fun brokerList(model: Model): String {
        // 必要に応じてブローカー一覧を取得してモデルに追加
        // model.addAttribute("brokers", ...)
        return "brokers"
    }

    @PostMapping("/brokers")
    fun brokerRegister(): String {
        // ブローカー登録処理（必要に応じて実装）
        return "redirect:/brokers"
    }
}
