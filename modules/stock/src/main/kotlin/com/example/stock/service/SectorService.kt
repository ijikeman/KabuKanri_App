package com.example.stock.service
import com.example.stock.model.Stock
import com.example.stock.model.Sector
import com.example.stock.repository.SectorRepository
import org.springframework.stereotype.Service

@Service
class SectorService(private val sectorRepository: SectorRepository) {
    fun findById(id: Int): Sector? {
        return sectorRepository.findById(id).orElse(null)
    }
    
    fun findAll(): List<Sector> {
        return sectorRepository.findAll()
    }

    fun findByName(name: String): Sector? {
        return sectorRepository.findByName(name)
    }

    fun save(sector: Sector): Sector {
        return sectorRepository.save(sector)
    }
}
