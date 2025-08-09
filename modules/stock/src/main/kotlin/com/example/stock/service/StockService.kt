package com.example.stock.service
import com.example.stock.model.Stock
import com.example.stock.repository.StockRepository
import com.example.stock.provider.YahooFinanceProvider
import org.springframework.stereotype.Service

import java.util.*

@Service
class StockService(
    private val stockRepository: StockRepository,
    private val yahooFinanceProvider: YahooFinanceProvider // ← 追加
) {
    fun findById(id: Int): Stock? {
        return stockRepository.findById(id).orElse(null)
    }

    fun findAll(): List<Stock> {
        return stockRepository.findAll()
    }

    fun findByCode(code: String): Stock? {
        return stockRepository.findByCode(code)
    }

    fun findByName(name: String): Stock? {
        return stockRepository.findByName(name)
    }

    // 追加する
    fun save(stock: Stock): Stock {
        return stockRepository.save(stock)
    }

    // current_priceを1つだけ更新する
    fun updatePrice(id: Int): Stock? {
        val stock = stockRepository.findById(id)
        return if (stock.isPresent) {
            val newPrice = yahooFinanceProvider.fetchStockPrice(stock.get().code, stock.get().country)
            if (newPrice != null) {
                val updatedStock = stock.get().copy(current_price = newPrice)
                stockRepository.save(updatedStock)
            } else {
                null
            }
        } else {
            null
        }
    }

    // 複数の銘柄のcurrent_priceを更新する(updatePriceを呼び出す)
    fun updatePrices(ids: List<Int>) {
        for (id in ids) {
            updatePrice(id)
        }
    }

    fun deleteStock(id: Int) {
        stockRepository.deleteById(id)
    }
}
