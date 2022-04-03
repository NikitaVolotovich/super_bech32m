object InputProcess {
    const val DEC_FORMAT = 1
    const val BASE64_FORMAT = 2
    const val HEX_FORMAT = 3
    const val BINARY_FORMAT = 4

    private var isToEncrypt: Boolean = false
    private var isToDecrypt: Boolean = false
//    private var isPrintIntoFile: Boolean = false
//    private var isReadFromFile: Boolean = false
    private var isInputCorrect: Boolean = true


    private var inputString: String = ""
    private var inputFilePath: String = ""
    private var outputFilePath: String = ""

    private var inputEncodeType: Int = DEC_FORMAT
    private var inputDecodeType: Int = BASE64_FORMAT
    private var outputEncodeType: Int = BASE64_FORMAT
    private var outputDecodeType: Int = DEC_FORMAT


    fun inputArgumentsExecutor(args: Array<String>) { // Mikita
        inputArgumentsInspector(args)
        if(isToEncrypt) {
            val bechData = BechTools.parseDecStringToBechData(inputString)
            val encrypted = Encode.encode(bechData.humanReadablePart, bechData.data)
            if(outputFilePath.isNotEmpty())
                FileInspector.writeStringIntoFile(outputFilePath, encrypted)
            else
                println(encrypted)
        } else if (isToDecrypt) {
            val decrypted = Decode.decodingString(inputString)
            if(outputFilePath.isNotEmpty())
                FileInspector.writeStringIntoFile(outputFilePath, decrypted.toString())
            else
                println(decrypted)
        }

    }
    private fun inputArgumentsInspector(args: Array<String>) { //Mikita
        if(args.isEmpty()){
            printPrompt("Please, write any arguments.")
            isInputCorrect = false
        }
    
        args.forEachIndexed { _, value ->
            when (value) {
                "-e"-> {
                    encodingInspector(args)
                    return@forEachIndexed
                }
                "-d" -> {
                    decodingInspector(args)
                    return@forEachIndexed
                }
            }
        }
    }

    private fun encodingInspector(args: Array<String>) {
        isToEncrypt = true
        args.forEachIndexed { index, value ->
            when (value) {
                "-e" -> {
                    if(index + 1 < args.size && args[index+1] != "-f") {
                        inputString = args[index + 1]
                        println("String for encoding: $inputString")
                    } else {
                        isInputCorrect = false
                    }
                }
                "-f" -> {
                    if(index + 1 < args.size) {
                        inputFilePath = args[index + 1]
                        println("Input filepath for encoding: $inputFilePath")
                    } else {
                        isInputCorrect = false
                    }
                }
                "-o" -> {
                    if(index + 1 < args.size) {
                        outputFilePath = args[index + 1]
                        println("Output filepath for encoding: $outputFilePath")
                    } else {
                        println("Input is incorrect. Filepath for output file was not found.")
                        isInputCorrect = false
                    }
                }
            }
        }
    }

    private fun decodingInspector(args: Array<String>) {
        isToDecrypt = true
        args.forEachIndexed { index, value ->
            when (value) {
                "-d" -> {
                    if(index + 1 < args.size && args[index+1] != "-f") {
                        inputString = args[index + 1]
                        println("String for decoding: $inputString")
                    }
                }
                "-f" -> {
                    if(index + 1 < args.size) {
                        inputFilePath = args[index + 1]
                        println("Input filepath for decoding: $inputFilePath")
                    }
                }
                "-o" -> {
                    if(index + 1 < args.size) {
                        outputFilePath = args[index + 1]
                        println("Output filepath for decoding: $outputFilePath")
                    }
                }
            }
        }
    }


    private fun printPrompt(errorString: String) {
        println(errorString)
        println("-e/-d str -> en/decrypt str and print it into terminal")
        println("-e/-d str -o filepath-> en/decrypt str and  put it into file in filepath")
        println("-e/-d -f filepath -> read filepath for each str in file and en/decrypt it and print into terminal")
        println("-e/-d -f filepath1 -o filepath2 -> read filepath1 for each str in file and en/decrypt it and put it")
        println("\tinto file in filepath2")
    }

    private fun readNewInput() {
        TODO("Not yet implemented")
    }

}