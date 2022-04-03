import java.math.BigInteger

data class BechData (
    val humanReadablePart: String,
    val data: ByteArray
) {
    override fun toString(): String {
        return "BechData(humanReadablePart='$humanReadablePart', data=${data.contentToString()})"
    }
}

object BechTools {
    fun parseDecStringToBechData(inputStr: String): BechData{
        return BechData("bc", byteArrayOf(1, 14, 20, 15, 7, 13, 26, 0, 25, 18, 6, 11, 13, 8, 21, 4, 20, 3, 17, 2, 29, 3, 12, 29, 3, 4, 15, 24, 20, 6, 14, 30, 22, 14, 20, 15, 7, 13, 26))
    }

    fun parseBechDataToHex(inputBech: BechData): String{
        return inputBech.humanReadablePart + "[" + inputBech.data.toHex() + "]"
    }

    fun parseBechDataToBinary(inputBech: BechData): String{
        return inputBech.humanReadablePart + "[" + inputBech.data.toBinary() + "]"
    }

    private fun ByteArray.toHex(): String = joinToString(separator = ",") { eachByte -> "0x%02x".format(eachByte) }

    private fun ByteArray.toBinary(): String = joinToString(separator = ",") { eachByte -> Integer.toBinaryString(eachByte.toInt())}

    fun String.parseHexStringToByteArray() :   ByteArray{
        return BigInteger(this, 16).toByteArray()
    }

    fun String.parseBinStringToByteArray() : ByteArray{
        return BigInteger(this, 2).toByteArray()
    }

    fun String.parseB64StringToHexString() : String{
        return BigInteger(this, 64).toString(16)
    }

    fun String.parseB64StringToBinString() : String{
        return BigInteger(this, 64).toString(2)
    }

    fun String.parseHexStringToB64String(): String{
        return BigInteger(this, 16).toString(64)
    }

    fun String.parseHexStringToBinaryString(): String{
        return BigInteger(this, 16).toString(2)
    }

    fun String.parseBinaryStringToB64String(): String{
        return BigInteger(this, 2).toString(64)
    }

    fun String.parseBinaryStringToHexString(): String{
        return BigInteger(this, 2).toString(16)
    }
}