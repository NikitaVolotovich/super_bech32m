package test

import Decode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DecodeTest{
    private val testEncode: Decode = Decode()

    @Test
    fun isZero() {
        val expected = true
        assertEquals(expected, testEncode.isZero(40,0))
    }
}