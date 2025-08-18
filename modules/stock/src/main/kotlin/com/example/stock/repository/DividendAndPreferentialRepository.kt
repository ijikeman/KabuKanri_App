package com.example.stock.repository

import com.example.stock.model.DividendAndPreferential
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 配当・優待履歴（DividendAndPreferential）エンティティの永続化を管理するリポジトリ。
 */
@Repository
interface DividendAndPreferentialRepository : JpaRepository<DividendAndPreferential, Int> {
}
