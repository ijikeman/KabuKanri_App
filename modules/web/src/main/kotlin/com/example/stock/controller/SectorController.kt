package com.example.stock.controller

import com.example.stock.service.SectorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class SectorController(
    private val sectorService: SectorService
) {
    @GetMapping("/sectors")
    fun sectorList(model: Model): String {
        // 必要に応じてセクター一覧を取得してモデルに追加
        // model.addAttribute("sectors", sectorService.findAll())
        return "sectors"
    }

    @PostMapping("/sectors")
    fun sectorRegister(): String {
        // セクター登録処理（必要に応じて実装）
        return "redirect:/sectors"
    }
}
