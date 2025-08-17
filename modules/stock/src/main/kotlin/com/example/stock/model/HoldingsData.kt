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
import java.time.LocalDateTime

/**
 * 保有銘柄エンティティ
 * どの所有者がどの銘柄をどれだけ保有しているかを表します。
 */
@Entity
@Table(name = "holdings")
data class Holdings(
    /** 保有ID (主キー) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    /** 株式を保有する所有者 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    val owner: Owner,

    /** 保有されている銘柄 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    val stock: Stock,

    /** 現在の保有株数 */
    @Column(name = "quantity", nullable = false)
    val quantity: Int,

    /** 平均取得単価 (手数料込み) */
    @Column(name = "average_price", nullable = false)
    val averagePrice: Double,

    /** 受け取った配当金の合計 */
    @Column(name = "total_dividend")
    val totalDividend: Int? = 0,

    /** 受け取った株主優待の合計金額 */
    @Column(name = "total_preferential")
    val totalPreferential: Int? = 0,

    /** レコードの最終更新日時 */
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)
