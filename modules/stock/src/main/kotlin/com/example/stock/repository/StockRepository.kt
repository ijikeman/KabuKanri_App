package com.example.stock.repository

import com.example.stock.model.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : JpaRepository<Stock, Int> {
    // 銘柄コードで株式を検索
    fun findByCode(code: String): Stock?

    // 銘柄名で株式を検索
    fun findByName(name: String): Stock?
}
