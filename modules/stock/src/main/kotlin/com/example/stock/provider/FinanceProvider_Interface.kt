package com.example.stock.provider

interface FinanceProvider {
    /**
     * 指定した銘柄コードと国に対応する現在の株価を返します。
     *
     * @param code 銘柄コード
     * @param country 国名
     * @return 現在の株価（取得できない場合は null）
     */
    fun fetchStockInfo(code: String, country: String): StockInfo?
}
