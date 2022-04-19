import java.math.BigInteger
import java.util.*

data class BechData(
    val humanReadablePart: String,
    val data: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BechData

        if (humanReadablePart != other.humanReadablePart) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = humanReadablePart.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}

object BechTools {
    const val NO_FORMAT = 0
    const val DEC_FORMAT = 1
    const val BASE64_FORMAT = 2
    const val HEX_FORMAT = 3
    const val BINARY_FORMAT = 4

    fun convertStringToBechData(inputStr: String, outputFormat: Int): BechData {
        /* Expected inputStr:
            dec: bc[1, 31, 20, 15, 7, 13]
            hex: bc[01,0e,14,0f,07,0d]
            bin: bc[00001,11111,10100,01111,00111,01101]
         */
        val array = inputStr.substringAfter('[').dropLast(1).split(',')
        val humanReadablePart = inputStr.substringBefore('[').trim()

        when (outputFormat) {
            DEC_FORMAT -> {
                return (BechData(humanReadablePart, array.map { BigInteger(it.trim(), 10).toByte() }.toByteArray()))
            }
            HEX_FORMAT -> {
                return (BechData(humanReadablePart, array.map { BigInteger(it.trim(), 16).toByte() }.toByteArray()))
            }
            BINARY_FORMAT -> {
                return (BechData(humanReadablePart, array.map { BigInteger(it.trim(), 2).toByte() }.toByteArray()))
            }
            BASE64_FORMAT -> {
                return (BechData(
                    humanReadablePart,
                    Base64.getDecoder().decode(inputStr.substringAfter('[').dropLast(1))
                ))
            }
        }
        Logger.write("Error in convertToBechData, 'when' block not executed")
        return (BechData("error", byteArrayOf(1, 2, 3)))
    }

    fun convertBechDataToString(bech: BechData, outputFormat: Int): String {
        when (outputFormat) {
            DEC_FORMAT -> {
                return "${bech.humanReadablePart}${bech.data.contentToString()}"
            }
            HEX_FORMAT -> {
                return parseBechDataToHex(bech)
            }
            BINARY_FORMAT -> {
                return parseBechDataToBinary(bech)
            }
            BASE64_FORMAT -> {
                return "${bech.humanReadablePart}[${Base64.getEncoder().encodeToString(bech.data)}]"
            }
        }
        return "false"
    }

    private fun parseBechDataToHex(inputBech: BechData): String {
        return inputBech.humanReadablePart + "[" + inputBech.data.toHex() + "]"
    }

    private fun parseBechDataToBinary(inputBech: BechData): String {
        return inputBech.humanReadablePart + "[" + inputBech.data.toBinary() + "]"
    }

    private fun ByteArray.toHex(): String = joinToString(separator = ",") { eachByte -> "%02x".format(eachByte) }

    private fun ByteArray.toBinary(): String = joinToString(separator = ",") { eachByte ->
        "%5s".format(Integer.toBinaryString(eachByte.toInt())).replace(' ', '0')
    }
}
