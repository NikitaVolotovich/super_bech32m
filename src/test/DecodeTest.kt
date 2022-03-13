package test

import Decode
import org.junit.jupiter.api.Test

internal object DecodeTest{
    @Test
    fun decodeInvalidCharacterNotInAlphabet() {
        try {
            Decode.decodingString("A12OUEL5X")
        } catch(e: Exception) {

        }
    }

    @Test
    fun decodeInvalidCharacterUpperLowerMix() {
        try {
            Decode.decodingString("A12UeL5X")
        } catch(e: Exception) {

        }
    }

    @Test
    fun decodeInvalidNetwork() {
        try {
            Decode.decodingString("A12UEL5X")
        } catch(e: Exception) {

        }
    }

    @Test
    fun decodeInvalidHRP() {
        try {
            Decode.decodingString("1pzry9x0s0muk")
        } catch(e: Exception) {

        }
    }
}
