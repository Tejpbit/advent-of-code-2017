
class Day1 : Day<String> {
    override fun parse(input: String): String {
        return input
    }

    override fun part1(input: String): Any {
        var sum = 0
        var currentMatchSum = 0
        var current = input[input.length-1]
        for (next in input) {
            if (current == next) {
                currentMatchSum += next.toInt() - 48
            } else {
                sum += currentMatchSum
                currentMatchSum = 0
            }
            current = next
        }
        sum += currentMatchSum
        return sum
    }

    override fun part2(input: String): Any {
        var matchOffSet = input.length/2
        var sum = 0
        var current_match_sum = 0

        for (current in input) {
            val match = input[matchOffSet]
            if (current == match) {
                current_match_sum += current.toInt() - 48
            } else {
                sum += current_match_sum
                current_match_sum = 0
            }
            matchOffSet = (matchOffSet + 1)  % input.length
        }

        sum += current_match_sum
        return sum
    }
}