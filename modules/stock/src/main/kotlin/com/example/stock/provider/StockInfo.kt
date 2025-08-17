package com.example.stock.provider

import java.time.LocalDate

data class StockInfo(
    val price: Int?,
    val dividend: Double?,
    val earningsDate: LocalDate?
)
