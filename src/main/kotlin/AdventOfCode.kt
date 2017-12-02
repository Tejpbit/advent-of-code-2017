import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.measureNanoTime

val dayInstances = hashMapOf(
        "01" to Day1(),
        "02" to Day2()
)

interface Day {
    //fun parse(input: String): T
    fun part1(input: String): Any
    fun part2(input: String): Any
}

fun main(args: Array<String>) {

    val time = measureNanoTime {

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd")
        var dayOfMonth = current.format(formatter)

        println("Current Date is: $dayOfMonth")

        if (args.isNotEmpty() && "\\d\\d".toRegex().matches(args[0])) {
            dayOfMonth = args[0]
            println("Day from args $dayOfMonth")
        }

        val daysData = loadRawData("input/day$dayOfMonth.dat")

        val d = dayInstances[dayOfMonth]

        println(d?.part1(daysData))

        println(d?.part2(daysData))

    }

    println("Main running time $time")
}

fun loadRawData(path: String): String {
    val workingDir = System.getProperty("user.dir")
    val f = File(workingDir + "/" + path)
    return f.readText()
}

fun formatNanoAsMilli(duration: Long): String {
    return "${duration/1000000} ms"
}