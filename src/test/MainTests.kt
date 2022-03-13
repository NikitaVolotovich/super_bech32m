package test

import Decode
import Encode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.util.*

internal class MainTests {
    @Test
    fun valid() {
        for (valid in VALID_INPUTS) {
            val bechData = Decode.decodingString(valid)
            var recode = Encode.encode(bechData.humanReadablePart, bechData.data)
            assertEquals(valid.lowercase(Locale.getDefault()), recode.lowercase(Locale.getDefault()), "Failed to round trip '$valid' -> '$recode'")

            // Test encoding with an uppercase HRP
            recode = Encode.encode(bechData.humanReadablePart.uppercase(Locale.getDefault()), bechData.data)
            assertEquals(valid.lowercase(Locale.getDefault()), recode.lowercase(Locale.getDefault()), "Failed to round trip '$valid' -> '$recode'")
        }
    }

    @Test
    fun invalid() {
        for (invalid in NOT_VALID_INPUTS) {
            try {
                Decode.decodingString(invalid)
                fail("Parsed an invalid code: '$invalid'")
            } catch (x: Exception) {
                /* expected */
            }
        }
    }

    companion object {
        private val VALID_INPUTS = arrayOf(
            "A12UEL5L",
            "a12uel5l",
            "an83characterlonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1tt5tgs",
            "abcdef1qpzry9x8gf2tvdw0s3jn54khce6mua7lmqqqxw",
            "11qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqc8247j",
            "split1checkupstagehandshakeupstreamerranterredcaperred2y9e3w",
            "?1ezyfcl"
        )

        private val NOT_VALID_INPUTS = arrayOf(
            " 1nwldj5", // HRP character out of range
            String(charArrayOf(0x7f.toChar())) + "1axkwrx", // HRP character out of range
            String(charArrayOf(0x80.toChar())) + "1eym55h", // HRP character out of range
            "an84characterslonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1569pvx", // overall max length exceeded
            "pzry9x0s0muk", // No separator character
            "1pzry9x0s0muk", // Empty HRP
            "x1b4n0q5v", // Invalid data character
            "li1dgmt3", // Too short checksum
            "de1lg7wt" + String(charArrayOf(0xff.toChar())), // Invalid character in checksum
            "A1G7SGD8", // checksum calculated with uppercase form of HRP
            "10a06t8", // empty HRP
            "1qzzfhee" // empty HRP
        )
    }
}