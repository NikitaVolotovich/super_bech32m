package test

import Decode
import Encode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class MainTests {
    @Test
    fun valid() {
        for (valid in VALID_INPUTS) {
            try {
                val bechData = Decode.decodingString(valid)
                var recode = Encode.encode(bechData.humanReadablePart, bechData.data)
                assertEquals(
                    valid.lowercase(Locale.getDefault()),
                    recode.lowercase(Locale.getDefault()),
                    "Failed to round trip '$valid' -> '$recode'"
                )

                // Test encoding with an uppercase HRP
                recode = Encode.encode(bechData.humanReadablePart.uppercase(Locale.getDefault()), bechData.data)
                assertEquals(
                    valid.lowercase(Locale.getDefault()),
                    recode.lowercase(Locale.getDefault()),
                    "Failed to round trip '$valid' -> '$recode'"
                )
            } catch(e: Exception) {
                println("Test 'valid': FAILED")
                println(e.message)
                return
            }
        }
        println("Test 'valid': SUCCESS")
    }

    @Test
    fun invalid() {
        for (invalid in NOT_VALID_INPUTS) {
            try {
                Decode.decodingString(invalid)
                println("Test 'invalid': FAILED")
                println("Parsed an invalid code: '$invalid'")
            } catch (e: Exception) {
                println("Test 'invalid'[$invalid]: SUCCESS")
                println(e.message)
                return
            }
        }
    }

    companion object {
        private val VALID_INPUTS = arrayOf(
            "4ef47f6eb681d5d9fa2f7e16336cd629303c635e8da51e425b76088be9c8744c",
            "514a33f1d46179b89e1fea7bbb07b682ab14083a276979f91038369d1a8d689b",
            "bc1qar0srrr7xfkvy5l643lydnw9re59gtzzwf5mdq",
            "bc1qc7slrfxkknqcq2jevvvkdgvrt8080852dfjewde450xdlk4ugp7szw5tk9",
            "bc1qc7slrfxkknqcq2jevvvkdgvrt8080852dfjewde450xdlk4ugp7szw5tk9",
            "A12UEL5L",
            "a12uel5l",
            "an83characterlonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1tt5tgs",
            "abcdef1qpzry9x8gf2tvdw0s3jn54khce6mua7lmqqqxw",
            "11qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqc8247j",
            "split1checkupstagehandshakeupstreamerranterredcaperred2y9e3w",
            "?1ezyfcl"
        )

        private val NOT_VALID_INPUTS = arrayOf(
            " 1nwldj5", // human-readable part character out of range
            String(charArrayOf(0x7f.toChar())) + "1axkwrx", // human-readable part character out of range
            String(charArrayOf(0x80.toChar())) + "1eym55h", // human-readable part character out of range
            "an84characterslonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1569pvx", // max length exceeded
            "pzry9x0s0muk", // No separator character
            "1pzry9x0s0muk", // Empty human-readable part
            "x1b4n0q5v", // Invalid data character
            "li1dgmt3", // Too short checksum
            "de1lg7wt" + String(charArrayOf(0xff.toChar())), // Invalid character in checksum
            "A1G7SGD8", // checksum calculated with uppercase form of human-readable part
            "10a06t8", // empty human-readable part
            "1qzzfhee" // empty human-readable part
        )
    }
}