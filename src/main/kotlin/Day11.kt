import kotlin.math.absoluteValue

class Day11: Day<List<String>> {

    override fun parse(input: String): List<String> {
        return input.split(",")
    }

    fun manhattan_dist(x: Int, y: Int, z: Int): Int {
        return (x.absoluteValue +
                y.absoluteValue +
                z.absoluteValue) / 2
    }

    var max = 0

    override fun part1(input: List<String>): Any {
        var x = 0
        var y = 0
        var z = 0

        input.forEach {
            when(it) {
                "n"  -> {     y++; z--}
                "ne" -> {x++;      z--}
                "se" -> {x++; y--     }
                "s"  -> {     y--; z++}
                "sw" -> {x--;      z++}
                "nw" -> {x--; y++;    }
            }
            max = kotlin.math.max(max, manhattan_dist(x, y, z))
        }

        return (x.absoluteValue +
                y.absoluteValue +
                z.absoluteValue) / 2
    }

    override fun part2(input: List<String>): Any {
        return max
    }

}
