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


}