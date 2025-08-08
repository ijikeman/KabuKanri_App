package com.example.stock.repository

import com.example.stock.model.Sectors
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SectorRepository : JpaRepository<Sectors, Int> {
    fun findByName(name: String): Sectors?
}
