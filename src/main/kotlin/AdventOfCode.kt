import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.measureNanoTime

val dayInstances = arrayOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5(),
        Day6(),
        Day7(),
        Day8(),
        Day9(),
        Day10(),
        Day11(),
        Day12(),
        Day13(),
        Day14(),
        Day15(),
        Day16(),
        Day17(),
        Day18(),
        Day19(),
        Day20(),
        Day21(),
        Day22(),
        Day23(),
        Day24(),
        Day25()
)

interface Day<T> {
    fun parse(input: String): T
    fun parse2(input: String): T {return parse(input)}
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

    run(day)
}

fun run(dayOfMonth: Int) {

    val time = measureNanoTime {
        val daysData = loadRawData("input/day${dayOfMonth.toString().padStart(2, '0')}.dat")

        val d = dayInstances[dayOfMonth - 1] as Day<Any>
        val parsed1 = d.parse(daysData)
        val parsed2 = d.parse2(daysData)

        println("Part1 answer: ${d.part1(parsed1)}")
        println("Part2 answer: ${d.part2(parsed2)}")

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