import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.measureNanoTime

val dayInstances = hashMapOf(
        "01" to Day1(),
        "02" to Day2()
)

interface Day<T> {
    fun parse(input: String): T
    fun part1(input: T): Any
    fun part2(input: T): Any
}

fun main(args: Array<String>) {
    val current = LocalDateTime.now()

    val formatter = DateTimeFormatter.ofPattern("dd")
    var dayOfMonth = current.format(formatter)

    println("Current Date is: $dayOfMonth")

    if (args.isNotEmpty() && "\\d\\d".toRegex().matches(args[0])) {
        dayOfMonth = args[0]
        println("Day from args $dayOfMonth")
    }

    val day = dayOfMonth.toInt()
    if (day !in 1..25) {
        println("Day $day outside [1,25]")
        return
    }

    run(dayOfMonth)
}

fun run(dayOfMonth: String) {

    val time = measureNanoTime {
        val daysData = loadRawData("input/day$dayOfMonth.dat")

        val d = dayInstances[dayOfMonth] as Day<Any>
        val parsed = d.parse(daysData)

        println(d.part1(parsed))
        println(d.part2(parsed))

    }

    println("Main running time ${time/1000000}ms\n${time}ns")
}

fun loadRawData(path: String): String {
    val workingDir = System.getProperty("user.dir")
    val f = File(workingDir + "/" + path)
    return f.readText()
}

fun formatNanoAsMilli(duration: Long): String {
    return "${duration/1000000} ms"
}