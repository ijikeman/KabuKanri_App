package com.example.stock.repository

import com.example.stock.model.Holdings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 保有銘柄（Holdings）エンティティの永続化を管理するリポジトリ。
 */
@Repository
interface HoldingsRepository : JpaRepository<Holdings, Int> {
    /**
     * 指定された所有者IDに紐づくすべての保有銘柄を検索します。
     * @param ownerId 所有者のID
     * @return 見つかった保有銘柄のリスト
     */
    fun findByOwnerId(ownerId: Int): List<Holdings>

    /**
     * 指定された所有者IDと銘柄IDに合致する保有銘柄を検索します。
     * @param ownerId 所有者のID
     * @param stockId 銘柄のID
     * @return 見つかった保有銘柄。存在しない場合はnull。
     */
    fun findByOwnerIdAndStockId(ownerId: Int, stockId: Int): Holdings?
}
