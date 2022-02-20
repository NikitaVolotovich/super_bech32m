package test

import Encode
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EncodeTest {
    private val testEncode: Encode = Encode()

    @Test
    fun sum() {
        val expected = 42
        assertEquals(expected, testEncode.sum(40,2))
    }
}