package com.example.stock.provider

import com.example.stock.model.Stocks
import org.springframework.stereotype.Component

@Component
class YahooFinanceProvider: FinanceProvider {
    // Yahoo Finance APIを使用して株価データを取得する実装
    // ここでは仮の実装として、固定の価格を返す
    override fun fetchStockPrice(code: String, country: String): Int? {
        // Yahoo Finance APIを使用して株価データを取得するロジックを実装
        // 渡された銘柄コードと国名に基づいて株価を取得
        return 1000 // 仮の価格
    }
}
