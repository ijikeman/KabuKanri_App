package com.example.stock.service

import com.example.stock.entity.Owners
import com.example.stock.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(private val ownerRepository: OwnerRepository) {
    fun findAll(): List<Owners> {
        return ownerRepository.findAll()
    }

    fun findByName(name: String): Owners? {
        return ownerRepository.findByName(name)
    }

    fun save(owner: Owners): Owners {
        return ownerRepository.save(owner)
    }
}
