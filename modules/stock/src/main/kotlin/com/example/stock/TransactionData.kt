package com.example.stock

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Column
import java.time.LocalDate

@Entity
@Table(name = "transaction_data")
data class TransactionData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_id", nullable = false)
    val security: SecurityData,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", nullable = false)
    val broker: BrokerData,

    @Column(name = "purchase_price", nullable = false)
    val purchasePrice: Int,

    @Column(name = "sale_price")
    val salePrice: Int? = null,

    @Column(name = "number_of_stocks", nullable = false)
    val numberOfStocks: Int,

    @Column(name = "purchase_date", nullable = false)
    val purchaseDate: LocalDate,

    @Column(name = "sale_date")
    val saleDate: LocalDate? = null,

    @Column(name = "buy_fee", nullable = false)
    val buyFee: Int,

    @Column(name = "sell_fee")
    val sellFee: Int? = null,

    @Column(name = "current_price", nullable = false)
    val currentPrice: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    val owner: OwnerData
)
