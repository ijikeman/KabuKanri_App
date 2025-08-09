package com.example.stock.controller

import com.example.stock.model.Owner
import com.example.stock.service.OwnerService
import org.springframework.stereotype.Controller
import com.example.stock.model.Stock
import com.example.stock.model.Sector
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
    @GetMapping("/stock")
    fun stockList(model: Model): String {
        // ここで株式のデータを取得し、モデルに追加します。
        // 例えば、株式のリストを取得するサービスを呼び出す
        model.addAttribute("stock", stockService.findAll())
        return "stock"
    }
    // 株式登録ページを表示するメソッド
    @GetMapping("/stock/register")
    fun stockRegisterForm(): String {
        // 株式登録フォームを表示するためのビュー名を返します。
        return "stockRegister"
    }
    // 株式登録処理を行うメソッド
    @PostMapping("/stock/register")
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
        // Stockオブジェクトを作成し、StockServiceを使って保存します        
        stockService.save(
            Stock(
                code = code,
                name = name,
                country = country,
                sector_id = sector
            )
        )
        // 保存後、株式一覧画面にリダイレクトします。
        return "redirect:/stock"
    }

    // 株式更新ページを表示するメソッド
    @PostMapping("/stock/updatePrice")
    fun updatePrice(@RequestParam("selectedIds") selectedIds: List<Int>): String {
        // selectedIds にはチェックされた銘柄コードのIDリストが格納されています。
        if (selectedIds.isEmpty()) {
            // チェックされた銘柄がない場合は、エラーメッセージを表示するか、適切な処理を行います。
            throw IllegalArgumentException("No stock selected for price update.")
        } else {
            // チェックされた銘柄コードをログに出力します。
            println("Selected stock Ids for price update: $selectedIds")
            // ここで株価更新のロジックを実装します。
            stockService.updatePrices(selectedIds)
        }
        return "redirect:/stock"
    }

    // オーナー関連のメソッド
    @GetMapping("/owner")
    fun ownerList(model: Model): String {
        val owner = ownerService.findAll()
        model.addAttribute("owner", owner)
        return "owner"
    }

    @PostMapping("/owner")
    fun ownerRegister(@RequestParam name: String): String {
        val owner = Owner(name = name)
        ownerService.save(owner)
        // 保存後、一覧画面にリダイレクトします。
        return "redirect:/owner"
    }

    @GetMapping("/broker")
    fun brokerList(): String {
        return "broker"
    }

    @PostMapping("/broker")
    fun brokerRegister(): String {
        return "redirect:/broker"
    }

    @GetMapping("/sector")
    fun sectorList(): String {
        return "sector"
    }

    @PostMapping("/sector")
    fun sectorRegister(): String {
        return "redirect:/sector"
    }
}
