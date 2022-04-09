//Valid bech32 vector
const val testVector5 = "bc1pw508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7k7grplx"
const val bech32Vector2 = "bc1p0xlxvlhemja6c4dqv22uapctqupfhlxm9h8z3k2e72q4k9hcz7vqh2y7hd"
const val bech32Vector3 = "tb1z0xlxvlhemja6c4dqv22uapctqupfhlxm9h8z3k2e72q4k9hcz7vqglt7rf"

//Valid bech32m vector
const val bech32mVector1 = "tb1q0xlxvlhemja6c4dqv22uapctqupfhlxm9h8z3k2e72q4k9hcz7vq24jc47"
const val bech32mVector2 = "an83characterlonghumanreadablepartthatcontainsthetheexcludedcharactersbioandnumber11sg7hg6"
const val bech32mVector3 = "split1checkupstagehandshakeupstreamerranterredcaperredlc445v"

fun main(args: Array<String>) {
    Logger.clean()
    var arguments: ArrayList<String> = ArrayList()
    arguments.addAll(args)

//    println("Input: $testVector5")
//    val decoded = Decode.decodingString(testVector5)
//    println(decoded)
//    val encoded = Encode.encode(humanReadablePart = decoded.humanReadablePart, data = decoded.data)
//    println(encoded)

    while(true){
        if(InputProcess.inputArgumentsExecutor(arguments)) break
        arguments = ArrayList()
        arguments.addAll(UserCommunicator.readNewInput())
    }
}
