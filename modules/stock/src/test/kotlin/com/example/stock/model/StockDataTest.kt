package com.example.stock.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows // assertThrowsテストの為
import org.junit.jupiter.api.Test

class StockDataTest {
    @Test
    fun `Stockエンティティのコンストラクタとプロパティが正しく動作すること`() {
        val sector = Sectors(
            id = 1,
            name = "Software"
        )

        val stock = Stocks(
            id = 1,
            name = "Test Stock",
            code = "1234",
            country = "jp",
            sector_id = sector, // Sectorsエンティティを参照
            current_price = 1000,
        )

        assertEquals(1, stock.id)
        assertEquals("Test Stock", stock.name)
        assertEquals("1234", stock.code)
        assertEquals("jp", stock.country)
        assertEquals("Software", stock.sector_id.name)
        assertEquals(1000, stock.current_price)
    }
}
