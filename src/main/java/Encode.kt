import java.util.*

object Encode {
    private const val CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l" // Bech32 character set for encoding

    fun encode(humanReadablePart: String, data: ByteArray): String {
        var humanPart = humanReadablePart

        check(humanPart.isNotEmpty()) { "Human-readable part is too short" }
        check(humanPart.length <= 83) { "Human-readable part is too long" }

        humanPart = humanPart.lowercase(Locale.getDefault())

        val checksum = CheckSum.getChecksum_bech32m(humanPart, data)
        val combined = ByteArray(data.size + checksum.size)
        data.copyInto(combined)
        checksum.copyInto(combined, startIndex = 0, destinationOffset = data.size)

        val stringBuilder = StringBuilder(humanPart.length + 1 + combined.size)
        stringBuilder.append(humanPart)
        stringBuilder.append('1')
        for (b in combined) {
            stringBuilder.append(CHARSET[b.toInt()])
        }
        return stringBuilder.toString()
    }
}