object UserCommunicator {
    fun askUserConsent(reasonStr: String): Boolean{
        println("Dear user, $reasonStr, \n Do you want to continue? (Y/n)")
        var inputString = readLine()
        return inputString == "Y"
    }


}