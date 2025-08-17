package com.example.stock.service

import com.example.stock.model.Sector
import com.example.stock.model.Stock
import com.example.stock.provider.StockInfo
import com.example.stock.provider.YahooFinanceProvider
import com.example.stock.repository.StockRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class StockServiceTest {

    // モック対象のクラス
    @MockK
    private lateinit var stockRepository: StockRepository

    @MockK
    private lateinit var yahooFinanceProvider: YahooFinanceProvider

    // テスト対象のクラス（上記モックを注入）
    @InjectMockKs
    private lateinit var stockService: StockService

    @BeforeEach
    fun setUp() {
        // 各テストの前にMockKを初期化
        MockKAnnotations.init(this)
    }

    @Test
    fun `updateStockDetail should update stock with fetched info`() {
        // Arrange
        // テスト用のデータ準備
        val sector = Sector(id = 1, name = "Technology")
        val originalStock = Stock(id = 1, code = "7203", name = "Toyota", country = "jp", sector_id = sector)
        val fetchedStockInfo = StockInfo(price = 3000, dividend = 100.5, earningsDate = LocalDate.of(2025, 10, 26))
        val stockSlot = slot<Stock>()

        // モックの動作設定
        every { stockRepository.findById(1) } returns Optional.of(originalStock)
        every { yahooFinanceProvider.fetchStockInfo("7203", "jp") } returns fetchedStockInfo
        every { stockRepository.save(capture(stockSlot)) } returns originalStock // 更新されたStockオブジェクトをキャプチャ

        // Act
        // テスト対象メソッドの実行
        val result = stockService.updateStockDetail(1)

        // Assert
        // 検証
        assertNotNull(result)
        verify(exactly = 1) { stockRepository.findById(1) }
        verify(exactly = 1) { yahooFinanceProvider.fetchStockInfo("7203", "jp") }
        verify(exactly = 1) { stockRepository.save(any()) }

        // 保存されたStockオブジェクトの検証
        val capturedStock = stockSlot.captured
        assertEquals(3000, capturedStock.current_price)
        assertEquals(100.5, capturedStock.latestDividend)
        assertEquals(LocalDate.of(2025, 10, 26), capturedStock.earningsDate)
        assertNotNull(capturedStock.lastUpdated)
    }
}
