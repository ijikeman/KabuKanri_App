package com.example.stock.service
import com.example.stock.model.Stocks
import com.example.stock.repository.StockRepository
import com.example.stock.provider.YahooFinanceProvider
import org.springframework.stereotype.Service

import java.util.*

@Service
class StockService(
    private val stockRepository: StockRepository,
    private val yahooFinanceProvider: YahooFinanceProvider // ← 追加
) {
    fun findById(id: Int): Stocks? {
        return stockRepository.findById(id).orElse(null)
    }

    fun findAll(): List<Stocks> {
        return stockRepository.findAll()
    }

    fun findByCode(code: String): Stocks? {
        return stockRepository.findByCode(code)
    }

    fun findByName(name: String): Stocks? {
        return stockRepository.findByName(name)
    }

    // 追加する
    fun save(stock: Stocks): Stocks {
        return stockRepository.save(stock)
    }

    // current_priceを更新する
    fun updateCurrentPrice(id: Int): Stocks? {
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

    fun deleteStock(id: Int) {
        stockRepository.deleteById(id)
    }
}
