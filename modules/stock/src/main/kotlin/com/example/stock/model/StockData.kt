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
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "stocks")
data class Stocks(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:NotBlank(message = "codeは必須です")
    @field:Pattern(regexp = "^[a-zA-Z0-9]+$", message = "codeは英数字のみで構成される必要があります")
    @Column(name = "code", nullable = false, unique = true)
    val code: String,

    @field:NotBlank(message = "nameは必須です")
    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @field:NotBlank(message = "countryは必須です")
    @field:Pattern(regexp = "^(jp|us)$", message = "countryは'jp'または'us'のいずれかである必要があります")
    @Column(name = "country", nullable = false)
    val country: String,

    // 現在の株価は更新があるため、nullableをfalseに設定
    @field:Min(0, message = "current_priceは0以上の数字である必要があります")
    @Column(name = "current_price", nullable = false)
    val current_price: Int = 0,

    @field:NotNull(message = "sector_idは必須です")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false)
    val sector_id: Sectors
)