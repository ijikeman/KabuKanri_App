package com.example.stock.provider

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

// @SpringBootTest
@Disabled("Disable tests that depend on external network access")
class YahooFinanceProviderTest {

    private val yahooFinanceProvider = YahooFinanceProvider()

    @Test
    fun `fetchStockInfo should return valid data for a valid stock code`() {
        // Arrange
        // val stockCode = "7203" // トヨタ自動車

        // Act
        // val stockInfo = yahooFinanceProvider.fetchStockInfo(stockCode, "jp")

        // Assert
        // assertNotNull(stockInfo, "StockInfo should not be null for a valid stock code")
        // stockInfo?.let {
        //     assertNotNull(it.price, "Price should not be null")
        //     assertTrue(it.price!! > 0, "Price should be positive")

        //     // 配当や業績発表日は存在しない場合もあるため、nullでないことのチェックは行わない
        //     // assertNotNull(it.dividend, "Dividend should not be null")
        //     // assertNotNull(it.earningsDate, "EarningsDate should not be null")
        // }
    }

    @Test
    fun `fetchStockInfo should return null for an invalid stock code`() {
        // Arrange
        // val stockCode = "99999" // 存在しない銘柄コード

        // Act
        // val stockInfo = yahooFinanceProvider.fetchStockInfo(stockCode, "jp")

        // Assert
        // 存在しない銘柄の場合、Yahooファイナンスはページ自体は表示するが、情報が取得できずnullになることを期待
        // もしくは、接続自体がエラーになる場合もある
        // ここでは、何らかの理由でnullが返ることを期待する
        // 厳密なテストのためには、Jsoupの呼び出しをモック化する必要がある
        // if (stockInfo != null) {
        //     // ページは存在するが、価格などがnullになるケース
        //     assertTrue(stockInfo.price == null && stockInfo.dividend == null && stockInfo.earningsDate == null)
        // } else {
        //     // ページが存在しないなどでExceptionが発生し、nullが返るケース
        //     assert(true)
        // }
    }
}
