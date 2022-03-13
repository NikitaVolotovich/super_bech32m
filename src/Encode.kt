import java.util.*

object Encode {
    private const val CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l" // Bech32 character set for encoding

    fun encode(humanReadablePart: String, data: ByteArray): String {
        var hrp = humanReadablePart

        check(hrp.isNotEmpty()) { "Human-readable part is too short" }
        check(hrp.length <= 83) { "Human-readable part is too long" }

        hrp = hrp.lowercase(Locale.getDefault())

        val checksum = CheckSum.createChecksum(hrp, data)
        val combined = ByteArray(data.size + checksum.size)
        data.copyInto(combined)
        checksum.copyInto(combined, startIndex = 0, destinationOffset = data.size)

        val sb = StringBuilder(hrp.length + 1 + combined.size)
        sb.append(hrp)
        sb.append('1')
        for (b in combined) {
            sb.append(CHARSET[b.toInt()])
        }
        return sb.toString()
    }
}