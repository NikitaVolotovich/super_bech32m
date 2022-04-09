import org.junit.jupiter.api.Test

internal class ToolsTests {

    @Test
    fun conversionTests(){
        val bech = BechData("piy", byteArrayOf(31, 14, 15, 20, 30))

        val inDec = BechTools.convertBechDataToString(bech, BechTools.DEC_FORMAT)
        println(inDec)
        val inHex = BechTools.convertBechDataToString(bech, BechTools.HEX_FORMAT)
        println(inHex)
        val inBin = BechTools.convertBechDataToString(bech, BechTools.BINARY_FORMAT)
        println(inBin)
        val inB64 = BechTools.convertBechDataToString(bech, BechTools.BASE64_FORMAT)
        println(inB64)

        val decToBech = BechTools.convertStringToBechData(inDec, BechTools.DEC_FORMAT)
        println(decToBech)
        val hexToBech = BechTools.convertStringToBechData(inHex, BechTools.HEX_FORMAT)
        println(hexToBech)
        val binToBech = BechTools.convertStringToBechData(inBin, BechTools.BINARY_FORMAT)
        println(binToBech)
        val b64ToBech = BechTools.convertStringToBechData(inB64, BechTools.BASE64_FORMAT)
        println(b64ToBech)
        assert(true)
    }
}