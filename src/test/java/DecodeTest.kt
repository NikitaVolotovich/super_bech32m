import Decode
import org.junit.jupiter.api.Test

internal object DecodeTest{
    @Test
    fun decodeInvalidCharacterNotInAlphabet() {
        try {
            Decode.decodingString("A1LQFN3A")
        } catch(e: Exception) {
            println("Test 'decodeInvalidCharacterNotInAlphabet': FAILED")
            println(e.message)
            return
        }
        println("Test 'decodeInvalidCharacterNotInAlphabet': SUCCESS")
    }

    @Test
    fun decodeInvalidCharacterUpperLowerMix() {
        try {
            Decode.decodingString("a1lqfn3a")
        } catch(e: Exception) {
            println("Test 'decodeInvalidCharacterUpperLowerMix': FAILED")
            println(e.message)
            return
        }
        println("Test 'decodeInvalidCharacterUpperLowerMix': SUCCESS")
    }

    @Test
    fun decodeInvalidNetwork() {
        try {
            Decode.decodingString("A1LQFN3A")
        } catch(e: Exception) {
            println("Test 'decodeInvalidNetwork': FAILED")
            println(e.message)
            return
        }
        println("Test 'decodeInvalidNetwork': SUCCESS")
    }

    @Test
    fun decodeInvalidHumanPart() {
        try {
            Decode.decodingString("a1lqfn3a")
        } catch(e: Exception) {
            println("Test 'decodeInvalidHumanPart': FAILED")
            println(e.message)
            return
        }
        println("Test 'decodeInvalidHumanPart': SUCCESS")
    }
}
