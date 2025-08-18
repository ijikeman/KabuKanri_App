package com.example.stock.controller

import com.example.stock.model.Transaction
import com.example.stock.service.BrokerService
import com.example.stock.service.OwnerService
import com.example.stock.service.StockService
import com.example.stock.service.TransactionService
import com.example.stock.service.HoldingsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

/**
 * 株式の売買取引に関連するリクエストを処理するコントローラー。
 */
@Controller
class TransactionController(
    private val transactionService: TransactionService,
    private val ownerService: OwnerService,
    private val stockService: StockService,
    private val brokerService: BrokerService,
    private val holdingsService: HoldingsService // holdingsRepositoryからholdingsServiceに変更
) {

    /**
     * 株式購入フォームページへのGETリクエストを処理します。
     * @param ownerId 所有者ID（任意）。指定されている場合、フォームの所有者ドロップダウンで初期選択されます。
     * @param model ビューに渡すデータを格納するモデル
     * @return 表示するビューの名前（"buy_form"）
     */
    @GetMapping("/transaction/buy")
    fun showBuyForm(@RequestParam(name = "ownerId", required = false) ownerId: Int?, model: Model): String {
        // フォームのドロップダウンに必要なリストをモデルに追加
        model.addAttribute("owners", ownerService.findAll())
        model.addAttribute("stocks", stockService.findAll())
        model.addAttribute("brokers", brokerService.findAll())
        if (ownerId != null) {
            model.addAttribute("selectedOwnerId", ownerId)
        }
        return "buy_form"
    }

    /**
     * 株式購入フォームからのPOSTリクエストを処理します。
     * @return 処理後にリダイレクトするURL
     */
    @PostMapping("/transaction/buy")
    fun processBuyTransaction(
        @RequestParam ownerId: Int,
        @RequestParam stockId: Int,
        @RequestParam brokerId: Int,
        @RequestParam quantity: Int,
        @RequestParam pricePerShare: Int,
        @RequestParam fee: Int,
        @RequestParam transactionAt: LocalDate
    ): String {
        // リクエストパラメータから関連エンティティを検索
        val owner = ownerService.findById(ownerId) ?: throw IllegalArgumentException("無効な所有者IDです")
        val stock = stockService.findById(stockId) ?: throw IllegalArgumentException("無効な銘柄IDです")
        val broker = brokerService.findAll().find { it.id == brokerId } ?: throw IllegalArgumentException("無効な証券会社IDです")

        // 新しい購入取引オブジェクトを作成
        val transaction = Transaction(
            owner = owner,
            stock = stock,
            broker = broker,
            quantity = quantity,
            pricePerShare = pricePerShare,
            fee = fee,
            transactionAt = transactionAt,
            transactionType = "BUY"
        )
        // 取引処理サービスを呼び出し
        transactionService.processTransaction(transaction)

        // 取引した所有者のポートフォリオページにリダイレクト
        return "redirect:/holdings?ownerId=$ownerId"
    }

    /**
     * 株式売却フォームページへのGETリクエストを処理します。
     * @param holdingId 売却対象の保有ID
     * @param model ビューに渡すデータを格納するモデル
     * @return 表示するビューの名前（"sell_form"）
     */
    @GetMapping("/transaction/sell")
    fun showSellForm(@RequestParam holdingId: Int, model: Model): String {
        val holding = holdingsService.findById(holdingId) ?: throw IllegalArgumentException("無効な保有IDです")
        model.addAttribute("holding", holding)
        model.addAttribute("brokers", brokerService.findAll())
        return "sell_form"
    }

    /**
     * 株式売却フォームからのPOSTリクエストを処理します。
     * @return 処理後にリダイレクトするURL
     */
    @PostMapping("/transaction/sell")
    fun processSellTransaction(
        @RequestParam holdingId: Int,
        @RequestParam brokerId: Int,
        @RequestParam quantity: Int,
        @RequestParam pricePerShare: Int,
        @RequestParam fee: Int,
        @RequestParam transactionAt: LocalDate
    ): String {
        // 売却対象の保有情報を取得
        val holding = holdingsService.findById(holdingId) ?: throw IllegalArgumentException("無効な保有IDです")
        val owner = holding.owner
        val stock = holding.stock
        val broker = brokerService.findAll().find { it.id == brokerId } ?: throw IllegalArgumentException("無効な証券会社IDです")

        // 新しい売却取引オブジェクトを作成
        val transaction = Transaction(
            owner = owner,
            stock = stock,
            broker = broker,
            quantity = quantity,
            pricePerShare = pricePerShare,
            fee = fee,
            transactionAt = transactionAt,
            transactionType = "SELL"
        )
        // 取引処理サービスを呼び出し
        transactionService.processTransaction(transaction)

        // 取引した所有者のポートフォリオページにリダイレクト
        return "redirect:/holdings?ownerId=${owner.id}"
    }
}
