object CheckSum {
    fun checkChecksum(humanPart: String, values: ByteArray): Boolean {
        val humanPartExpanded = expandHumanPart(humanPart)
        val combined = ByteArray(humanPartExpanded.size + values.size)
        humanPartExpanded.copyInto(combined)
        values.copyInto(combined, destinationOffset = humanPartExpanded.size)
        return findPolynomial(combined) == 1
    }

    fun getChecksum(humanPart: String, values: ByteArray): ByteArray {
        val humanPartExpanded = expandHumanPart(humanPart)
        val enc = ByteArray(humanPartExpanded.size + values.size + 6)
        humanPartExpanded.copyInto(enc)
        values.copyInto(enc, startIndex = 0, destinationOffset = humanPartExpanded.size)

        val mod = findPolynomial(enc) xor 1
        val ret = ByteArray(6)
        for (i in 0..5) {
            ret[i] = (mod.ushr(5 * (5 - i)) and 31).toByte()
        }
        return ret
    }

    private fun findPolynomial(values: ByteArray): Int { // mod the generator as 30-bit
        var coefficient = 1
        for (v_i in values) {
            val c0 = coefficient.ushr(25) and 0xff
            coefficient = coefficient and 0x1ffffff shl 5 xor (v_i.toInt() and 0xff)
            if (c0 and 1 != 0) coefficient = coefficient xor 0x3b6a57b2
            if (c0 and 2 != 0) coefficient = coefficient xor 0x26508e6d
            if (c0 and 4 != 0) coefficient = coefficient xor 0x1ea119fa
            if (c0 and 8 != 0) coefficient = coefficient xor 0x3d4233dd
            if (c0 and 16 != 0) coefficient = coefficient xor 0x2a1462b3
        }
        return coefficient
    }

    private fun expandHumanPart(humanPart: String): ByteArray {
        val humanPartLength = humanPart.length
        val byteArray = ByteArray(humanPartLength * 2 + 1)
        for (i in 0 until humanPartLength) {
            val c = humanPart[i].code and 0x7f // Limit 7-bit ASCII
            byteArray[i] = (c.ushr(5) and 0x07).toByte()
            byteArray[i + humanPartLength + 1] = (c and 0x1f).toByte()
        }
        byteArray[humanPartLength] = 0
        return byteArray
    }
}
