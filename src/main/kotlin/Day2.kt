
class Day2 {

    fun parse(input: String): List<List<Int>> {
        val lines = input.lines()

        return lines.map {
            it.split("\\s".toRegex())
                    .map { it.toInt() }
        }
    }

    fun part1(input: List<List<Int>>): Int {
        return input.sumBy { getRowDiff(it) }
    }

    fun part2(input: List<List<Int>>): Int {
        return input.sumBy { getRowDivisor(it) }
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