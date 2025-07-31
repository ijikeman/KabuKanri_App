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

@Entity
@Table(name = "security_data")
data class SecurityData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "code", nullable = false, unique = true)
    val code: Int,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "country_code", nullable = false)
    val countryCode: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false)
    val sector: SectorData
)
