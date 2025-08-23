package com.example.stock.service

import com.example.stock.model.DividendAndPreferential
import com.example.stock.repository.DividendAndPreferentialRepository
import org.springframework.stereotype.Service

@Service
class DividendAndPreferentialService(
    private val dividendAndPreferentialRepository: DividendAndPreferentialRepository
) {
    fun save(dividendAndPreferential: DividendAndPreferential) {
        dividendAndPreferentialRepository.save(dividendAndPreferential)
    }

    fun findAll(): List<DividendAndPreferential> {
        return dividendAndPreferentialRepository.findAll()
    }
}
