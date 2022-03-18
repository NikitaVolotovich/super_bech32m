object InputProcess {
    private fun inputArgumentsExecutor(args: Array<String?>) { // Mikita
        inputArgumentsInspector(args)

    }
    private fun inputArgumentsInspector(args: Array<String?>) { //Mikita
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
}