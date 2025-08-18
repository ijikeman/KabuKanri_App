package com.example.stock.model

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

/**
 * 配当金および株主優待の履歴エンティティ
 * どの銘柄からいつ配当や優待を受け取ったかを記録します。
 */
@Entity
@Table(name = "dividend_and_preferentials")
data class DividendAndPreferential(
    /** 履歴ID (主キー) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    /** 対象の銘柄 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    val stock: Stock,

    /** 受け取った配当金または優待の金額 */
    @Column(name = "dividend_price", nullable = false)
    val dividendPrice: Int,

    /** 受け取り時にかかった手数料（税金など） */
    @Column(name = "fee", nullable = false)
    val fee: Int,

    /** 受け取り日 */
    @Column(name = "transaction_at", nullable = false)
    val transactionAt: LocalDate
)
