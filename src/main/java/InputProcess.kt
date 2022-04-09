import BechTools.BASE64_FORMAT
import BechTools.BINARY_FORMAT
import BechTools.DEC_FORMAT
import BechTools.HEX_FORMAT
import BechTools.NO_FORMAT

// Written by Mikita 7.4.2022
/*
When function returned 'false' – it means FAIL
'true' – successful result
 */

object InputProcess {

    private var isToEncode: Boolean = false
    private var isToDecode: Boolean = false
    private var isPrintIntoFile: Boolean = false

    private var inputString: String = ""
    private var outputFilePath: String = ""

    private var inputFormat: Int = NO_FORMAT // Default: DEC for encode // BASE64 for decode
    private var outputFormat: Int = NO_FORMAT //Default: BASE64 for encode // DEC for decode

    private var inputArray: MutableList<String> = ArrayList()
    private var outputArray: MutableList<String> = ArrayList()

    fun inputArgumentsExecutor(args: ArrayList<String>): Boolean {
        if (!inputArgumentsInspector(args)) return false

        if (isToEncode) {
            val bechData = BechTools.convertStringToBechData(inputString, inputFormat)
            val encrypted = Encode.encode(bechData.humanReadablePart, bechData.data)
            if (isPrintIntoFile)
                FileInspector.writeStringIntoFile(outputFilePath, encrypted)
            else
                println(encrypted)
        } else if (isToDecode) {
            val decoded = Decode.decodingString(inputString)
            if (isPrintIntoFile)
                FileInspector.writeStringIntoFile(
                    outputFilePath,
                    BechTools.convertBechDataToString(decoded, outputFormat)
                )
            else
                println(BechTools.convertBechDataToString(decoded, outputFormat))
        }
        return true
    }

    private fun inputArgumentsInspector(args: ArrayList<String>): Boolean { //Mikita
        if (args.isEmpty()) {
            UserCommunicator.printPrompt("You didn't provide any arguments :(")
            return false
        }

        if (isArgIsHelp(args)) return false // if user ask for a help -> stop processing

        divideArray(args) // divide array into input part and output part

        if (!inputArrayInspector(inputArray)) return false
        if (!outputArrayInspector(outputArray)) return false

        return true
    }

    private fun divideArray(args: ArrayList<String>) {
        inputArray = ArrayList()
        outputArray = ArrayList()
        var switch = false
        args.forEachIndexed { _, value ->
            if (value == "-o")
                switch = true
            if (!switch)
                inputArray.add(value)
            else
                outputArray.add(value)
        }
    }

    private fun inputArrayInspector(args: MutableList<String>): Boolean {
        var isOperationSet = false
        var isFormatSet = false
        var isFileOutputSet = false
        var isStringSet = false
        args.forEachIndexed { _, value ->
            when (value) {
                "-e" -> {
                    if (!isOperationSet) {
                        isOperationSet = true
                        isToEncode = true
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                "-d" -> {
                    if (!isOperationSet) {
                        isOperationSet = true
                        isToDecode = true
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                "-f" -> {
                    if (!isFileOutputSet) {
                        isFileOutputSet = true
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                "-hex" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        inputFormat = HEX_FORMAT
                        Logger.write("Input format hex defined")
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                "-base64" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        inputFormat = BASE64_FORMAT
                        Logger.write("Input format base64 defined")
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                "-dec" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        inputFormat = DEC_FORMAT
                        Logger.write("Input format dec defined")
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                "-bin" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        inputFormat = BINARY_FORMAT
                        Logger.write("Input format bin defined")
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
                else -> {
                    if (value[0] != '-' && !isStringSet) {
                        isStringSet = true
                        inputString = value
                        Logger.write("Input string defined")
                    } else {
                        println("Wrong input part of args: $value")
                        return false
                    }
                }
            }
        }
        if (!isOperationSet || !isStringSet)
            return false

        if (!isFormatSet) {
            if (isToEncode)
                inputFormat = DEC_FORMAT
            else if (isToDecode)
                inputFormat = BASE64_FORMAT
        }

        if (isFileOutputSet) {
            inputString = FileInspector.readStringFromFile(inputString)
            if(inputString.isEmpty()) {
                println("Error: Input from a file is empty.")
                return false
            }
        }

        if (inputString.isEmpty())
            return false
        return true
    }

    private fun outputArrayInspector(args: MutableList<String>): Boolean {
        var isFormatSet = false
        var isStringSet = false

        if (args.isEmpty()) {
            println("No arguments for output. Default settings will used.")
            return true
        }

        args.forEachIndexed { _, value ->
            when (value) {
                "-hex" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        outputFormat = HEX_FORMAT
                        Logger.write("Output format hex defined")
                    } else {
                        println("Wrong output part of args: $value")
                        return false
                    }
                }
                "-base64" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        outputFormat = BASE64_FORMAT
                        Logger.write("Output format base64 defined")
                    } else {
                        println("Wrong output part of args: $value")
                        return false
                    }
                }
                "-dec" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        outputFormat = DEC_FORMAT
                        Logger.write("Output format dec defined")
                    } else {
                        println("Wrong output part of args: $value")
                        return false
                    }
                }
                "-bin" -> {
                    if (!isFormatSet) {
                        isFormatSet = true
                        outputFormat = BINARY_FORMAT
                        Logger.write("Output format bin defined")
                    } else {
                        println("Wrong output part of args: $value")
                        return false
                    }
                }
                else -> {
                    if (value[0] != '-' && !isStringSet) {
                        isStringSet = true
                        outputFilePath = value
                        Logger.write("Output filepath string defined")
                    } else if (value != "-o") {
                        println("Wrong output part of args: $value")
                        return false
                    }
                }
            }
        }

        if (!isFormatSet) {
            if (isToEncode)
                outputFormat = BASE64_FORMAT
            else if (isToDecode)
                outputFormat = DEC_FORMAT
        }
        if (isStringSet) {
            isPrintIntoFile = true
        }
        return true
    }


    private fun isArgIsHelp(args: MutableList<String>): Boolean {
        args.forEachIndexed { _, value ->
            when (value) {
                "-help" -> {
                    UserCommunicator.printHelp()
                    return true
                }
            }
        }
        return false
    }
}
