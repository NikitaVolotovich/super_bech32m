import java.io.File

object Logger {
    var file = File("log.txt")

    fun write(string: String){
        file.createNewFile()
        file.writeText(string)
    }
}