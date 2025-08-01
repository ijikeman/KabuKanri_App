package com.example.stock.repository

import com.example.stock.entity.Owners
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository : JpaRepository<Owners, Int> {
    fun findByName(name: String): Owners?
}
