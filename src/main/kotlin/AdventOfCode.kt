import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val current = LocalDateTime.now()

    val formatter = DateTimeFormatter.ofPattern("dd")
    val dayOfMonth = current.format(formatter)

    println("Current Date is: $dayOfMonth")

    val daysData = loadRawData("input/day$dayOfMonth.dat")

    when (dayOfMonth) {
        "01" -> {
            val d = Day1()
            println(d.calcCaptcha(daysData))
            println(d.calcCaptcha2(daysData))
        }
        "02" -> {
            val d = Day2()
            val parsed = d.parse(daysData)
            println(d.part1(parsed))
            println(d.part2(parsed))
        }
    }


}

fun loadRawData(path: String): String {
    val workingDir = System.getProperty("user.dir")
    val f = File(workingDir + "/" + path)
    return f.readText()
}