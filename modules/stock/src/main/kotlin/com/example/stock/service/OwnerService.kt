package com.example.stock.service

import com.example.stock.model.Owner
import com.example.stock.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(private val ownerRepository: OwnerRepository) {
    fun findAll(): List<Owner> {
        return ownerRepository.findAll()
    }

    fun findByName(name: String): Owner? {
        return ownerRepository.findByName(name)
    }

    // 追加する
    fun save(owner: Owner): Owner {
        return ownerRepository.save(owner)
    }
}
