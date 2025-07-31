package com.example.stock

import jakarta.persistence.*

@Entity
@Table(name = "sector_data")
data class SectorData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "name", nullable = false)
    val name: String
)
