package com.example.stock.provider

import java.time.LocalDate

data class StockInfo(
    val price: Double?,
    val dividend: Double?,
    val earningsDate: LocalDate?
)
