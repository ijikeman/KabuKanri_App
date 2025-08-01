package com.example.stock.repository

import com.example.stock.entity.Stocks
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : JpaRepository<Stocks, Int> {
    fun findByCode(code: Int): Stocks?
}
