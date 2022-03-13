package test

import Encode
import org.junit.jupiter.api.Test

internal object EncodeTest {

    @Test
    fun encodeCorrectInput(){
        val data = byteArrayOf(1, 14, 20, 15, 7, 13, 26, 0, 25, 18, 6, 11, 13, 8, 21, 4, 20, 3, 17, 2, 29, 3, 12, 29, 3, 4, 15, 24, 20, 6, 14, 30, 22, 14, 20, 15, 7, 13, 26, 0, 25, 18, 6, 11, 13, 8, 21, 4, 20, 3, 17, 2, 29, 3, 12, 29, 3, 4, 15, 24, 20, 6, 14, 30, 22)
        val humanReadablePart = "bc"

        try {
            Encode.encode(humanReadablePart = humanReadablePart, data = data)
        } catch(e: Exception) {
            println("Test 'encodeCorrectInput': FAILED")
            println(e.message)
            return
        }
        println("Test 'encodeCorrectInput': SUCCESS")
    }
}