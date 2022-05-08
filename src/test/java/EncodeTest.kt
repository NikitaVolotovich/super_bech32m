import Encode
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class EncodeTest {

    @Test
    fun encodeCorrectInput(){
        val data = byteArrayOf(1, 14, 20, 15, 7, 13, 26, 0, 25, 18, 6, 11, 13, 8, 21, 4, 20, 3, 17, 2, 29, 3, 12, 29, 3, 4, 15, 24, 20, 6, 14, 30, 22, 14, 20, 15, 7, 13, 26, 0, 25, 18, 6, 11, 13, 8, 21, 4, 20, 3, 17, 2, 29, 3, 12, 29, 3, 4, 15, 24, 20, 6, 14, 30, 22)
        val humanReadablePart = "bc"

        val encoded = Encode.encode(humanReadablePart = humanReadablePart, data = data)
        assertEquals(encoded, "bc1pw508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7kt5nd6y", message = "Test 'encodeCorrectInput': FAILED")
    }
}