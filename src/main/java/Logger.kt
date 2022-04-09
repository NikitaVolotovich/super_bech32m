import java.io.File
import java.time.Instant
import java.time.format.DateTimeFormatter

object Logger {
    private var file = File("log.txt")

    fun write(string: String) {
        file.appendText(string + "\n")
    }

    fun clean() {
        file.writeText(DateTimeFormatter.ISO_INSTANT.format(Instant.now()) + "\n")
    }
}
