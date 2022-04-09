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

    private val VALID_INPUTS = arrayOf(
        "an83characterlonghumanreadablepartthatcontainsthetheexcludedcharactersbioandnumber11sg7hg6",
        "abcdef1l7aum6echk45nj3s0wdvt2fg8x9yrzpqzd3ryx",
        "split1checkupstagehandshakeupstreamerranterredcaperredlc445v"
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