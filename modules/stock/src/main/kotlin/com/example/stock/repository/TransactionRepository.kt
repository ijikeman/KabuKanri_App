package com.example.stock.repository

import com.example.stock.entity.Transactions
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<Transactions, Int> {
    fun findByStockId(stockId: Int): List<Transactions>
}
