package com.example.stock.model

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OwnerDataTest {

    private lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Test
    fun `コンストラクタとプロパティが正しく動作すること`() {
        val owner = Owner(name = "testowner")
        assertEquals("testowner", owner.name)
        // idはInt型で、デフォルト値は0
        assertEquals(0, owner.id)
    }

    @Test
    fun `nameがアルファベット以外の場合はエラー`() {
        val owner = Owner(name = "testowner123")
        val violations = validator.validate(owner)
        assertFalse(violations.isEmpty())
        assertEquals(1, violations.size)
        assertEquals("名前はアルファベットのみで構成される必要があります", violations.iterator().next().message)
    }

    @Test
    fun `nameがブランクの場合はエラー`() {
        val owner = Owner(name = "")
        val violations = validator.validate(owner)
        assertEquals(2, violations.size)
        val messages = violations.map { it.message }
        assertTrue(messages.contains("名前は必須です")) // Blankの場合、2つエラーが返るので記載
        assertTrue(messages.contains("名前はアルファベットのみで構成される必要があります")) // Blankの場合、2つエラーが返るので記載
    }
}
