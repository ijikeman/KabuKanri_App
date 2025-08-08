package com.example.stock.controller

import com.example.stock.model.Owners
import com.example.stock.service.OwnerService
import org.springframework.stereotype.Controller
import com.example.stock.model.Stocks
import com.example.stock.model.Sectors
import com.example.stock.service.StockService
import com.example.stock.service.SectorService
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class StockController(
    private val ownerService: OwnerService, // ここでOwnerServiceをインジェクトします
    private val stockService: StockService, // ここでStockServiceをインジェクトします
    private val sectorService: SectorService // ここでSectorServiceをインジェクトします
) {
    // 株式一覧ページを表示するメソッド
    @GetMapping("/stocks")
    fun stockList(model: Model): String {
        // ここで株式のデータを取得し、モデルに追加します。
        // 例えば、株式のリストを取得するサービスを呼び出す
        model.addAttribute("stocks", stockService.findAll())
        return "stocks"
    }
    // 株式登録ページを表示するメソッド
    @GetMapping("/stocks/register")
    fun stockRegisterForm(): String {
        // 株式登録フォームを表示するためのビュー名を返します。
        return "stockRegister"
    }
    // 株式登録処理を行うメソッド
    @PostMapping("/stocks/register")
    fun stockRegister(
        @RequestParam code: String,
        @RequestParam name: String,
        @RequestParam country: String,
        @RequestParam sector_name: String
    ): String {
    // 株式の登録処理を行います。
        // sector_nameを使って、SectorServiceからセクターを取得します。
        val sector = sectorService.findByName(sector_name)
        if (sector == null) {
            // セクターが見つからない場合は、エラーメッセージを表示するか、適切な処理を行います。
            throw IllegalArgumentException("Sector not found: $sector_name")
        }
        // Stocksオブジェクトを作成し、StockServiceを使って保存します        
        stockService.save(
            Stocks(
                code = code,
                name = name,
                country = country,
                sector_id = sector
            )
        )
        // 保存後、株式一覧画面にリダイレクトします。
        return "redirect:/stocks"
    }

    // オーナー関連のメソッド
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
