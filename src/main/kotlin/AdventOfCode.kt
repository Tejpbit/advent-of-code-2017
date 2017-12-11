import java.io.File
import java.sql.Time
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.round
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


    val startLoadData = System.nanoTime()
    val daysData = loadRawData("input/day${dayOfMonth.toString().padStart(2, '0')}.dat")
    val endLoadData = System.nanoTime()

    val d = dayInstances[dayOfMonth - 1] as Day<Any>
    val startParse1 = System.nanoTime()
    val parsed1 = d.parse(daysData)
    val endParse1 = System.nanoTime()
    val startParse2 = System.nanoTime()
    val parsed2 = d.parse2(daysData)
    val endParse2 = System.nanoTime()

    val startPart1 = System.nanoTime()
    val ansPart1 = try {d.part1(parsed1)} catch (e: NotImplementedError) {null}
    val endPart1 = System.nanoTime()
    val startPart2 = System.nanoTime()
    val ansPart2 = try {d.part2(parsed2)} catch (e: NotImplementedError) {null}
    val endPart2 = System.nanoTime()

    println()
    println("Part 1 answer: $ansPart1")
    println("Part 2 answer: $ansPart2")

    val totalTime = endPart2 - startLoadData
    val loadDataTime = endLoadData - startLoadData
    val parse1Time = endParse1 - startParse1
    val parse2Time = endParse2 - startParse2
    val solve1Time = endPart1 - startPart1
    val solve2Time = endPart2 - startPart2
    println()
    println("Running times")

    println(java.lang.String.format("%6s%10s%10s%10s", "Name", "Time(ms)", "Time(µs)", "Time(ns)"))
    printFormatted("Total", totalTime)
    printFormatted("Load", loadDataTime)
    printFormatted("Parse1", parse1Time)
    printFormatted("Parse2", parse2Time)
    printFormatted("Solve1", solve1Time)
    printFormatted("Solve2", solve2Time)
}

fun loadRawData(path: String): String {
    val workingDir = System.getProperty("user.dir")
    val f = File(workingDir + "/" + path)
    return f.readText()
}

fun printFormatted(title: String, nanotime: Long) {
    val milliseconds = round(nanotime / 1000000.0).toInt()
    val microseconds = round(nanotime / 1000.0).toInt()
    println(java.lang.String.format("%6s%10d%10d%10d", title, milliseconds, microseconds, nanotime))
}
