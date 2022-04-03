import java.io.BufferedReader
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object FileInspector {
    const val UNEXPECTED_RESULT = 0
    const val FILEPATH_IS_DIRECTORY = 1
    const val FILE_EXISTS = 2
    const val FILE_DOESNT_EXIST = 3
    const val NO_ACCESS_TO_FILE = 4

    fun writeStringIntoFile(filePath: String, str: String) {
        val fileAvailabilityCode = isFileAvailable(filePath)
        if(fileAvailabilityCode != FILEPATH_IS_DIRECTORY && fileAvailabilityCode != NO_ACCESS_TO_FILE) {
            if(fileAvailabilityCode == FILE_DOESNT_EXIST) {
                File(filePath).bufferedWriter().use { out ->
                    out.write(str)
                }
            } else if (fileAvailabilityCode == FILE_EXISTS) {
                if (UserCommunicator.askUserConsent("file exists, all contents of the file will be cleared"))
                    File(filePath).bufferedWriter().use { out ->
                        out.write(str)
                    }
            }
        } else {
            println("Error: Filepath is directory or no access to it.")
        }
    }

    fun readStringFromFile(filePath: String): String {
        if(isFileAvailable(filePath) == FILE_EXISTS) {
            val bufferedReader: BufferedReader = File(filePath).bufferedReader()
            return bufferedReader.use { it.readText() }
        }
        println("Error: File for reading not exist")
        return ""
    }

    private fun isFileAvailable(filePath: String): Int {
        val path = Paths.get(filePath)

        return when {
            Files.isDirectory(path) -> {
                FILEPATH_IS_DIRECTORY
            }
            Files.exists(path) -> {
                FILE_EXISTS
            }
            Files.notExists(path) -> {
                FILE_DOESNT_EXIST
            }
            else -> {
                NO_ACCESS_TO_FILE
            }
        }
        return UNEXPECTED_RESULT
    }
}