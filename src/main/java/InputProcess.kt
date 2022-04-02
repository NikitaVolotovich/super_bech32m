object InputProcess {
    fun inputArgumentsExecutor(args: Array<String?>) { // Mikita
        inputArgumentsInspector(args)

    }
    private fun inputArgumentsInspector(args: Array<String?>) { //Mikita
        /*
        - no args -> show examples
        -e str -> encrypt str and print it into terminal
        -e str -o filepath-> encrypt str and  put it into file in filepath
        -e -f filepath -> read filepath for each str in file and encrypt it and print into terminal
        -e -f filepath1 -o filepath2 -> read filepath1 for each str in file and encrypt it and put it
            into file in filepath2
        */
        var isInputCorrect: Boolean = false



        if(args.isEmpty()){
            printPromt("Please, write any arguments.")
        }
//        var args: Array<String?>? = args
//        var correctArguments = true
//        val correctInputExample = "Example: text.txt --Title --Table_of_contents file.txt [...] (or \"exit\")."
//        while (true) {
//            if (!correctArguments) {
//                args = inputArgumentsReader()
//            }
//            if (args == null) {
//                System.err.println("Input is empty, please, write your arguments again.")
//                System.err.println(correctInputExample)
//                correctArguments = false
//                continue
//            }
//            if (!com.company.Main.detectSubparts(args)) {
//                System.err.println("Input finder didn't detect any subparts, please, write your arguments again.")
//                System.err.println(correctInputExample)
//                correctArguments = false
//                continue
//            }
//            if (!com.company.Main.detectFilePath(args)) {
//                System.err.println("Input finder didn't detect any files, please, try again.")
//                System.err.println(correctInputExample)
//                correctArguments = false
//                continue
//            }
//            break
//        }
    }

    private fun printPromt(errorString: String) {
        println(errorString)
        println("-e str -> encrypt str and print it into terminal")
        println("-e str -o filepath-> encrypt str and  put it into file in filepath")
        println("-e -f filepath -> read filepath for each str in file and encrypt it and print into terminal")
        println("-e -f filepath1 -o filepath2 -> read filepath1 for each str in file and encrypt it and put it")
        println("\tinto file in filepath2")
    }

    private fun readNewInput() {
        TODO("Not yet implemented")
    }

    private fun checkInputArguments(){

    }
}