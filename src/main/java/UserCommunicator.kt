import java.io.BufferedReader
import java.io.InputStreamReader

object UserCommunicator {
    fun askUserConsent(reasonStr: String): Boolean {
        println("Dear user, $reasonStr, \n Do you want to continue? (Y/n)")
        var inputString = readLine()
        return inputString == "Y"
    }

    fun printPrompt(errorString: String) {
        println(errorString)
        println("=== (: EXAMPLES OF USING: (also -help) :) ===")
        println("-e bc[10,21,31,1,0,30] -> encode and print it into terminal")
        println("-e -hex bc[0a,15,1f,01,00,1e] -o output.txt -> encode and put it into file")
        println("-d bc124lpq7lf6enj -o -bin -> decode and print it in binary format")
        println("-d -f -hex input.txt -o -base64 output.txt -> decode line from input.txt and put it into output.txt")
    }

    fun printHelp() {
        println("=== (: VERY HELPFUL HELP :) ===")
        println("-e = encoding")
        println("-d = decoding")
        println("-f = read first line from file")
        println("-o = after this argument are output settings")
        println("Supported formats:")
        println("-dec = input/output in decimal format")
        println("-hex = input/output in hexadecimal format")
        println("-bin = input/output in binary format")
        println("-base64 = input/output in base64 format")
    }

    fun readNewInput(): ArrayList<String> {
        println("Please, provide new arguments:")
        return ArrayList(BufferedReader(InputStreamReader(System.`in`)).readLine().split(' '))
    }
}
