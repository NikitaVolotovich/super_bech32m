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
        /* Expected inputStr:
            dec: bc[1, 31, 20, 15, 7, 13]
            hex: bc[01,0e,14,0f,07,0d]
            bin: bc[00001,11111,10100,01111,00111,01101]
         */
        val array = inputStr.substringAfter('[').dropLast(1).split(',')
        val humanReadablePart = inputStr.substringBefore('[').trim()

        when (inputFormat) {
            DEC_FORMAT -> {
                return(BechData(humanReadablePart, array.map{ BigInteger(it.trim(), 10).toByte() }.toByteArray()))
            }
            HEX_FORMAT -> {
                return(BechData(humanReadablePart, array.map{ BigInteger(it.trim(), 16).toByte() }.toByteArray()))
            }
            BINARY_FORMAT -> {
                return(BechData(humanReadablePart, array.map{ BigInteger(it.trim(), 2).toByte() }.toByteArray()))
            }
            BASE64_FORMAT -> {
                return(BechData(humanReadablePart, array.map{ BigInteger(it.trim(), 64).toByte() }.toByteArray()))
            }
        }

        Logger.write("Error in convertToBechData, 'when' block not executed")
        return(BechData("error", byteArrayOf(1,2,3)))
    }

    fun parseDecStringToBechData(inputStr: String): BechData{
        //TODO It might be changed to
        //return convertToBechData(inputStr, 1)
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