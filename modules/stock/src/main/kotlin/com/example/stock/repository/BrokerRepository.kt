package com.example.stock.repository

import com.example.stock.entity.Brokers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrokerRepository : JpaRepository<Brokers, Int> {
    fun findByName(name: String): Brokers?
}