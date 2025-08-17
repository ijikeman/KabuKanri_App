package com.example.stock.service

import com.example.stock.model.Holdings
import com.example.stock.repository.HoldingsRepository
import org.springframework.stereotype.Service

/**
 * 保有銘柄（Holdings）に関するビジネスロジックを管理するサービスクラス。
 */
@Service
class HoldingsService(
    private val holdingsRepository: HoldingsRepository
) {

    /**
     * 指定された所有者の保有銘柄リストを取得します。
     * @param ownerId 所有者のID
     * @return 保有銘柄のリスト
     */
    fun getHoldingsForOwner(ownerId: Int): List<Holdings> {
        return holdingsRepository.findByOwnerId(ownerId)
    }
}
