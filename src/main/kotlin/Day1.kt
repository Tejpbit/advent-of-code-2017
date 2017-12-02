
class Day1 {
    fun calcCaptcha(input: String): Int {
        var sum = 0
        var current_match_sum = 0
        var current = input[input.length-1]
        for (next in input) {
            if (current == next) {
                current_match_sum += next.toInt() - 48
            } else {
                sum += current_match_sum
                current_match_sum = 0
            }
            current = next
        }
        sum += current_match_sum
        return sum
    }

    fun calcCaptcha2(input: String): Int {
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