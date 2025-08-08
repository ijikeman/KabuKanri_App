package com.example.stock.service
import com.example.stock.model.Stocks
import com.example.stock.model.Sectors
import com.example.stock.repository.SectorRepository
import org.springframework.stereotype.Service

@Service
class SectorService(private val sectorRepository: SectorRepository) {
    fun findById(id: Int): Sectors? {
        return sectorRepository.findById(id).orElse(null)
    }
    
    fun findAll(): List<Sectors> {
        return sectorRepository.findAll()
    }

    fun findByName(name: String): Sectors? {
        return sectorRepository.findByName(name)
    }
}
