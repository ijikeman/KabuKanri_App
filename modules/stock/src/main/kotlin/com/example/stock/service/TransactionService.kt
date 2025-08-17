package com.example.stock.service

import com.example.stock.model.Transaction
import com.example.stock.repository.TransactionRepository
import com.example.stock.repository.HoldingsRepository
import com.example.stock.model.Holdings
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * 取引（Transaction）に関するビジネスロジックを管理するサービスクラス。
 */
@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val holdingsRepository: HoldingsRepository
) {

    /**
     * 新しい取引を処理し、関連する保有銘柄情報を更新します。
     * この操作はトランザクション内で実行され、途中でエラーが発生した場合はロールバックされます。
     * @param transaction 処理対象の取引オブジェクト
     */
    @Transactional
    fun processTransaction(transaction: Transaction) {
        // 1. 新しい取引履歴をデータベースに保存
        transactionRepository.save(transaction)

        // 2. 取引内容に応じて保有銘柄情報を更新
        val owner = transaction.owner
        val stock = transaction.stock
        val existingHolding = holdingsRepository.findByOwnerIdAndStockId(owner.id, stock.id)

        // 取引タイプで処理を分岐
        if (transaction.transactionType.equals("BUY", ignoreCase = true)) {
            // 購入取引の場合
            if (existingHolding != null) {
                // 既に同じ銘柄を保有している場合：保有情報を更新
                val oldQuantity = existingHolding.quantity
                val oldAveragePrice = existingHolding.averagePrice
                val newQuantity = oldQuantity + transaction.quantity

                // 新しい平均取得単価を計算（総購入金額 ÷ 総株数）
                val newTotalValue = (oldAveragePrice * oldQuantity) + (transaction.pricePerShare * transaction.quantity) + transaction.fee
                val newAveragePrice = newTotalValue / newQuantity

                val updatedHolding = existingHolding.copy(
                    quantity = newQuantity,
                    averagePrice = newAveragePrice,
                    updatedAt = LocalDateTime.now()
                )
                holdingsRepository.save(updatedHolding)
            } else {
                // 新規に銘柄を購入した場合：新しい保有情報を作成
                val averagePrice = (transaction.pricePerShare.toDouble() * transaction.quantity + transaction.fee) / transaction.quantity
                val newHolding = Holdings(
                    owner = owner,
                    stock = stock,
                    quantity = transaction.quantity,
                    averagePrice = averagePrice,
                    updatedAt = LocalDateTime.now()
                )
                holdingsRepository.save(newHolding)
            }
        } else if (transaction.transactionType.equals("SELL", ignoreCase = true)) {
            // 売却取引の場合
            if (existingHolding == null) {
                throw IllegalStateException("保有していない銘柄は売却できません。")
            }
            if (existingHolding.quantity < transaction.quantity) {
                throw IllegalStateException("保有株数を超える数量は売却できません。")
            }

            val newQuantity = existingHolding.quantity - transaction.quantity

            if (newQuantity == 0) {
                // 売却後に保有株数が0になった場合は、保有リストから削除
                holdingsRepository.delete(existingHolding)
            } else {
                // 保有株数が残っている場合は、数量を更新
                val updatedHolding = existingHolding.copy(
                    quantity = newQuantity,
                    updatedAt = LocalDateTime.now()
                )
                holdingsRepository.save(updatedHolding)
            }
        }
    }
}
