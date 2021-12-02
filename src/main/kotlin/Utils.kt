import java.io.File

fun readLinesFromFile(dayName: String): List<String>
        = File("src/main/kotlin/$dayName", "$dayName.txt").readLines()