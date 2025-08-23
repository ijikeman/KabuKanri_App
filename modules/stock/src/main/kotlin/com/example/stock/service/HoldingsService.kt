package com.example.stock.service

import com.example.stock.model.Holdings
import com.example.stock.repository.HoldingsRepository
import org.springframework.stereotype.Service

/**
 * 保有銘柄（Holdings）に関するビジネスロジックを管理するサービスクラス。
 */
@Service
open class HoldingsService(
    private val holdingsRepository: HoldingsRepository
) {

    /**
     * 指定された所有者の保有銘柄リストを取得します。
     * @param ownerId 所有者のID
     * @return 保有銘柄のリスト
     */
    open fun getHoldingsForOwner(ownerId: Int): List<Holdings> {
        return holdingsRepository.findByOwnerId(ownerId).filter { it.quantity > 0 }
    }

    /**
     * 保有IDに基づいて保有銘柄を検索します。
     * @param id 検索する保有銘柄のID
     * @return 見つかった保有銘柄。存在しない場合はnull。
     */
    open fun findById(id: Int): Holdings? {
        return holdingsRepository.findById(id).orElse(null)
    }
}
