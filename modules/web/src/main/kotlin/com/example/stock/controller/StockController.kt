package com.example.stock.controller

import org.springframework.stereotype.Controller
import com.example.stock.model.Stock
import com.example.stock.model.Sector
import com.example.stock.service.StockService
import com.example.stock.service.SectorService
import com.example.stock.provider.FinanceProvider
import com.example.stock.model.Broker
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class StockController(
    private val stockService: StockService,
    private val sectorService: SectorService,
    private val financeProvider: FinanceProvider,
) {
    @GetMapping("/api/stock/name")
    @ResponseBody
    fun getStockName(
        @RequestParam("code") code: String,
        @RequestParam("country") country: String
    ): Map<String, String> {
        val stockName = financeProvider.fetchStockName(code, country)
        return mapOf("name" to (stockName ?: ""))
    }

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
    fun stockRegisterForm(model: Model): String {
        model.addAttribute("sectors", sectorService.findAll())
        // 株式登録フォームを表示するためのビュー名を返します。
        return "stockRegister"
    }
    // 株式登録処理を行うメソッド
    @PostMapping("/stock/register")
    fun stockRegister(
        @RequestParam code: String,
        @RequestParam name: String,
        @RequestParam country: String,
        @RequestParam sector_id: Int
    ): String {
    // 株式の登録処理を行います。
        // sector_idを使って、SectorServiceからセクターを取得します。
        val sector = sectorService.findById(sector_id)
        if (sector == null) {
            // セクターが見つからない場合は、エラーメッセージを表示するか、適切な処理を行います。
            throw IllegalArgumentException("Sector not found: $sector_id")
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
    @PostMapping("/stock/update")
    fun updatePrice(
        redirectAttributes: RedirectAttributes, // Modelだとredirectの時にattributeは渡されないため、redirectAttributesに変更
        @RequestParam("selectedIds", required = false) selectedIds: List<Int>?
    ): String {
        val ids = selectedIds ?: emptyList()
        // selectedIds にはチェックされた銘柄コードのIDリストが格納されています。
        if (ids.isEmpty()) {
            println("Selected stock Ids for update: $ids")
            // チェックされた銘柄がない場合は、画面にエラーメッセージを表示する為、error_massage attributeを設定する
            redirectAttributes.addFlashAttribute("errorMessage", "更新する銘柄が選択されていません。") // model.addAttributeではredirectの時パラメータは渡されない
            return "redirect:/stock" // stock.html に戻る
        } else {
            // チェックされた銘柄コードをログに出力します。
            println("Selected stock Ids for update: $ids")
            // ここで株価更新のロジックを実装します。
            stockService.updateStockDetails(ids)
        }
        return "redirect:/stock"
    }
}
