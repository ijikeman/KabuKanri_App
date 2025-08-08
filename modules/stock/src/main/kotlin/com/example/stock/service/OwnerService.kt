package com.example.stock.service

import com.example.stock.model.Owners
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

    // 追加する
    fun save(owner: Owners): Owners {
        return ownerRepository.save(owner)
    }
}
