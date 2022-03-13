const val testVector5 = "bc1pw508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7k7grplx"

fun main() {
    println("PA193 course project start.")
    println("Input: $testVector5")
    val decoded = Decode.decodingString(testVector5)
    println(decoded)
    val encoded = Encode.encode(humanReadablePart = decoded.humanReadablePart, data = decoded.data)
    println(encoded)
}




