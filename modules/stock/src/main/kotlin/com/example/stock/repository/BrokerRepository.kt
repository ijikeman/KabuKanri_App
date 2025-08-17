package com.example.stock.repository

import com.example.stock.model.Broker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrokerRepository : JpaRepository<Broker, Int> {
    fun findByName(name: String): Broker?
}
