package com.example.stock.service
import com.example.stock.model.Stocks
import com.example.stock.repository.StockRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StockService(private val stockRepository: StockRepository) {
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

    fun deleteStock(id: Int) {
        stockRepository.deleteById(id)
    }
}
