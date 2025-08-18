package com.example.stock.service

import com.example.stock.model.Stock
import com.example.stock.repository.StockRepository
import com.example.stock.provider.YahooFinanceProvider
import org.springframework.stereotype.Service
import java.time.LocalDateTime

import java.util.*

@Service
open class StockService(
    private val stockRepository: StockRepository,
    private val yahooFinanceProvider: YahooFinanceProvider // ← 追加
) {
    open fun findById(id: Int): Stock? {
        return stockRepository.findById(id).orElse(null)
    }

    open fun findAll(): List<Stock> {
        return stockRepository.findAll()
    }

    open fun findByCode(code: String): Stock? {
        return stockRepository.findByCode(code)
    }

    open fun findByName(name: String): Stock? {
        return stockRepository.findByName(name)
    }

    // 追加する
    open fun save(stock: Stock): Stock {
        return stockRepository.save(stock)
    }

    /**
     * 指定されたIDの銘柄情報を更新します。
     * YahooFinanceProviderを使用して最新の情報を取得し、データベースを更新します。
     * @param id 更新する銘柄のID
     * @return 更新後の銘柄情報。銘柄が見つからない場合や情報取得に失敗した場合はnull。
     */
    open fun updateStockDetail(id: Int): Stock? {
        // IDで銘柄を検索
        val stockOptional = stockRepository.findById(id)
        if (!stockOptional.isPresent) {
            return null // 銘柄が見つからない場合はnullを返す
        }

        val stock = stockOptional.get()

        // プロバイダーから最新の財務情報を取得
        val stockInfo = yahooFinanceProvider.fetchStockInfo(stock.code, stock.country)

        return if (stockInfo != null) {
            // 取得した情報でStockエンティティを更新
            val updatedStock = stock.copy(
                current_price = stockInfo.price,
                latestDividend = stockInfo.dividend,
                earningsDate = stockInfo.earningsDate,
                lastUpdated = LocalDateTime.now() // 最終更新日時を現在時刻に設定
            )
            // 更新したエンティティを保存
            stockRepository.save(updatedStock)
        } else {
            null // 情報取得に失敗した場合はnullを返す
        }
    }

   /**
     * 複数の銘柄情報を一度に更新します。
     * @param ids 更新する銘柄のIDリスト
     */
    open fun updateStockDetails(ids: List<Int>) {
        for (id in ids) {
            updateStockDetail(id)
        }
    }

    open fun deleteStock(id: Int) {
        stockRepository.deleteById(id)
    }
}
