import Decode
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

internal class DecodeTest{
    @Test
    fun decodeInvalidCharacterNotInAlphabet() {
        assertFailsWith<Exception>(
            message = "No exception found : FAILED",
            block= {
                Decode.decodingString("y1b0jsk6g") //Invalid data character
            }
        )
    }

    @Test
    fun decodeInvalidCharacterUpperLowerMix() {
        assertFailsWith<Exception>(
            message = "No exception found : FAILED",
            block = {
                Decode.decodingString("A1LqFN3a") // Mixed case
            })
    }

    @Test
    fun decodeInvalidNetwork() {
        assertFailsWith<Exception>(
            message = "No exception found : FAILED",
            block = {
                Decode.decodingString("in1muywd") // inv. network
            })
    }

    @Test
    fun decodeInvalidHumanPart() {
        assertFailsWith<Exception>(
            message = "No exception found : FAILED",
            block = {
                Decode.decodingString("0x801vctc34") // inv. human part
            })
    }

    @Test
    fun decodeEmptyHumanPart() {
        assertFailsWith<Exception>(
            message = "No exception found : FAILED",
            block = {
                Decode.decodingString("1vctc34") // empty human part
            })
    }
}
