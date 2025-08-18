package com.example.stock.service

import com.example.stock.model.Broker
import com.example.stock.repository.BrokerRepository
import org.springframework.stereotype.Service

/**
 * 証券会社（Broker）に関するビジネスロジックを管理するサービスクラス。
 */
@Service
open class BrokerService(
    private val brokerRepository: BrokerRepository
) {
    /**
     * すべての証券会社情報を取得します。
     * @return 証券会社のリスト
     */
    open fun findAll(): List<Broker> {
        return brokerRepository.findAll()
    }

    /**
     * 新しい証券会社を保存します。
     * @param broker 保存する証券会社オブジェクト
     * @return 保存された証券会社オブジェクト
     */
    open fun save(broker: Broker): Broker {
        return brokerRepository.save(broker)
    }
}
