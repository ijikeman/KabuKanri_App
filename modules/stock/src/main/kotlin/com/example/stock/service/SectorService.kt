package com.example.stock.service
import com.example.stock.model.Stock
import com.example.stock.model.Sector
import com.example.stock.repository.SectorRepository
import org.springframework.stereotype.Service

@Service
open class SectorService(private val sectorRepository: SectorRepository) {
    open fun findById(id: Int): Sector? {
        return sectorRepository.findById(id).orElse(null)
    }
    
    open fun findAll(): List<Sector> {
        return sectorRepository.findAll()
    }

    open fun findByName(name: String): Sector? {
        return sectorRepository.findByName(name)
    }
}
