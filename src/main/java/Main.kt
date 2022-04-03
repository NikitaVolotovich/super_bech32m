import InputProcess.inputArgumentsExecutor
import java.io.BufferedReader
import java.io.File

const val testVector5 = "bc1pw508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7k7grplx"

fun main(args: Array<String>) {
    println("PA193 course project start.")
//    println("Input: $testVector5")
//    val decoded = Decode.decodingString(testVector5)
//    println(decoded)
//    val encoded = Encode.encode(humanReadablePart = decoded.humanReadablePart, data = decoded.data)
//    println(encoded)

//    inputArgumentsExecutor(args)
    println(BechData("bc", byteArrayOf(1, 14, 20, 15, 7, 13)).toString())
    println(BechTools.parseBechDataToHex(BechData("bc", byteArrayOf(1, 14, 20, 15, 7, 13))))
    println(BechTools.parseBechDataToBinary(BechData("bc", byteArrayOf(1, 14, 20, 15, 7, 13))))
}


