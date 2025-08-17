package com.example.stock.controller

import com.example.stock.service.HoldingsService
import com.example.stock.service.OwnerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * 保有銘柄（ポートフォリオ）表示に関連するリクエストを処理するコントローラー。
 */
@Controller
class HoldingsController(
    private val holdingsService: HoldingsService,
    private val ownerService: OwnerService
) {

    /**
     * ポートフォリオ表示ページ（/holdings）へのGETリクエストを処理します。
     *
     * @param ownerId 表示対象の所有者ID（リクエストパラメータ、任意）
     * @param model ビューに渡すデータを格納するモデル
     * @return 表示するビューの名前（"holdings"）
     */
    @GetMapping("/holdings")
    fun viewHoldings(@RequestParam(name = "ownerId", required = false) ownerId: Int?, model: Model): String {
        // ページ上のドロップダウンに表示するため、すべての所有者情報を取得してモデルに追加
        val allOwners = ownerService.findAll()
        model.addAttribute("owners", allOwners)

        // ownerIdが指定されている場合のみ、その所有者のポートフォリオを検索
        if (ownerId != null) {
            // holdingsServiceを使用して、指定されたIDの所有者が保有する銘柄リストを取得
            val holdings = holdingsService.getHoldingsForOwner(ownerId)
            model.addAttribute("holdings", holdings)
            // ドロップダウンで選択された値を維持するために、選択されたownerIdをモデルに追加
            model.addAttribute("selectedOwnerId", ownerId)
        }

        // "holdings.html" をレンダリングして表示
        return "holdings"
    }
}
