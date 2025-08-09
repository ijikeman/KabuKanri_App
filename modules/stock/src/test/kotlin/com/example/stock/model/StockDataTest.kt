package com.example.stock.model

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StockDataTest {
    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    // Sectorsエンティティのテスト用データ
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

    @Test
    fun `Stockエンティティのコンストラクタとプロパティが正しく動作すること`() {
        assertEquals(1, stock.id)
        assertEquals("Test Stock", stock.name)
        assertEquals("1234", stock.code)
        assertEquals("jp", stock.country)
        assertEquals("Software", stock.sector_id.name)
        assertEquals(1000, stock.current_price)
    }

    @Test
    fun `nameが日本語が正しく動作すること`() {
        val test_stock = stock.copy(name = "テスト株式")
        assertEquals("テスト株式", test_stock.name)
    }

    @Test
    fun `codeがアルファベットが正しく動作すること`() {
        val test_stock = stock.copy(code = "ABCD")
        assertEquals("ABCD", test_stock.code)
    }

    @Test
    fun `countryはjpは動作すること`() {
        val test_stock = stock.copy(country = "jp")
        assertEquals("jp", test_stock.country)
    }

    @Test
    fun `countryはusは動作すること`() {
        val test_stock = stock.copy(country = "us")
        assertEquals("us", test_stock.country)
    }

    @Test
    fun `countryはjpかus以外はエラーになること`() {
        val test_stock = stock.copy(country = "fr")
        val violations = validator.validate(test_stock)
        assertFalse(violations.isEmpty())
        val messages = violations.map { it.message }
        assertTrue(messages.contains("countryは'jp'または'us'のいずれかである必要があります"))
    }
}
