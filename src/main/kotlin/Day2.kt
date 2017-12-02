
class Day2: Day {

    fun parse(input: String): List<List<Int>> {
        val lines = input.lines()

        return lines.map {
            it.split("\\s".toRegex())
                    .map { it.toInt() }
        }
    }

    override fun part1(input: String): Any {
        val parsed = parse(input)
        return parsed.sumBy { getRowDiff(it) }
    }

    override fun part2(input: String): Int {
        val parsed = parse(input)
        return parsed.sumBy { getRowDivisor(it) }
    }

    fun getRowDiff(row: List<Int>): Int {
        val min = row.min() as Int
        val max = row.max() as Int
        return max - min
    }

    fun getRowDivisor(row: List<Int>): Int {
        row.forEach { x -> row.forEach{ y ->
            if  (x != y) {
                if (x % y == 0) return x/y
                if (y % x == 0) return y/x
            }
        } }

        return 0
    }


}