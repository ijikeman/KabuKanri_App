package com.example.stock.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows // assertThrowsテストの為
import org.junit.jupiter.api.Test

class OwnerDataTest {
    @Test
    fun `Ownersエンティティのコンストラクタとプロパティが正しく動作すること`() {
        val owner = Owners(name = "testowner")
        assertEquals("testowner", owner.name)
        // idはInt型で、デフォルト値は0
        assertEquals(0, owner.id)
    }

    @Test
    fun `Ownersエンティティのnameがブランクの場合IllegalArgumentExceptionをスローすること`() {
        val exception = assertThrows<IllegalArgumentException> {
            Owners(name = "")
        }
        assertEquals("Name cannot be blank", exception.message)
    }
}
