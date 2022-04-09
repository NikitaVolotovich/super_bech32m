object Decode {
    private val CHARSET_REV = byteArrayOf( // The Bech32 character set for decoding.
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, 15, -1, 10, 17, 21, 20, 26, 30, 7, 5, -1, -1, -1, -1, -1,
        -1, -1, 29, -1, 24, 13, 25, 9, 8, 23, -1, 18, 22, 31, 27, 19, -1, 1, 0, 3, 16, 11,
        28, 12, 14, 6, 4, 2, -1, -1, -1, -1, -1, -1, 29, -1, 24, 13, 25, 9, 8, 23, -1, 18,
        22, 31, 27, 19, -1, 1, 0, 3, 16, 11, 28, 12, 14, 6, 4, 2, -1, -1, -1, -1, -1
    )

    fun decodingString(stringToDecode: String): BechData {
        var lower = false
        var upper = false
        if (stringToDecode.length < 8)
            throw Exception("Input too short: " + stringToDecode.length)
        if (stringToDecode.length > 90)
            throw Exception("Input too long: " + stringToDecode.length)
        for (element in stringToDecode) {
            if (element.code < 33 || element.code > 126) throw Exception ("Character is invalid")
            if (element in 'a'..'z') {
                if (element.isUpperCase() || upper)
                    throw Exception("Character is in uppercase")
                lower = true
            }
            if (element in 'A'..'Z') {
                if (lower)
                    throw Exception("Character is in lowercase")
                upper = true
            }
        }
        val position = stringToDecode.lastIndexOf('1')
        if (position < 1) throw Exception ("Character of human part is in wrong position")
        val dataPartLength = stringToDecode.length - 1 - position
        if (dataPartLength < 6) throw Exception ("Data part too short")
        val values = ByteArray(dataPartLength)
        for (i in 0 until dataPartLength) {
            val c = stringToDecode[i + position + 1]
            if (CHARSET_REV[c.code].toInt() == -1) throw Exception( "Invalid character")
            values[i] = CHARSET_REV[c.code]
        }
        val humanReadablePart = stringToDecode.substring(0, position).lowercase()
        if (!CheckSum.checkChecksum_bech32m(humanReadablePart, values)) throw Exception ("Invalid checksum")
        return BechData(humanReadablePart, values.copyOfRange(0, values.size - 6))
    }
}
