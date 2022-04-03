import java.math.BigInteger

data class BechData (
    val humanReadablePart: String,
    val data: ByteArray
) {
    override fun toString(): String {
        return "$humanReadablePart${data.contentToString()})"
    }
}



object BechTools {
    const val DEC_FORMAT = 1
    const val BASE64_FORMAT = 2
    const val HEX_FORMAT = 3
    const val BINARY_FORMAT = 4

    fun convertToBechData(inputStr: String, inputFormat: Int): BechData{
        /*

         */
        when (inputFormat) {
            DEC_FORMAT -> {
                // parse input str to humanReadablePart and array as data
                var humanReadablePart = ""
                var array = byteArrayOf(10, 20, 31, 1, 2, 0)
                return(BechData(humanReadablePart, array))
            }
            HEX_FORMAT -> {

                return(BechData("ex", byteArrayOf(1,2,3)))
            }
            BINARY_FORMAT -> {
                //convert binary data to dec and do the same like in dec format
                return(BechData("ex", byteArrayOf(1,2,3)))
            }
        }
        Logger.write("Error in convertToBechData, 'when' block not executed")
        return(BechData("error", byteArrayOf(1,2,3)))
    }

    fun parseDecStringToBechData(inputStr: String): BechData{
        return BechData("bc", byteArrayOf(1, 14, 20, 15, 7, 13, 26, 0))
    }

    fun parseBechDataToHex(inputBech: BechData): String{
        return inputBech.humanReadablePart + "[" + inputBech.data.toHex() + "]"
    }

    fun parseBechDataToBinary(inputBech: BechData): String{
        return inputBech.humanReadablePart + "[" + inputBech.data.toBinary() + "]"
    }

    private fun ByteArray.toHex(): String = joinToString(separator = ",") { eachByte -> "%02x".format(eachByte) }

    private fun ByteArray.toBinary(): String = joinToString(separator = ",") { eachByte -> "%5s".format(Integer.toBinaryString(eachByte.toInt())).replace(' ', '0')}

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