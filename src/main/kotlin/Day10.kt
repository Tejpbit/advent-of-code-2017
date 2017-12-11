
class Day10: Day<List<Int>> {
    override fun parse(input: String): List<Int> {
        return input.split(',').map(String::toInt).toList()
    }

    override fun parse2(input: String): List<Int> {
        return input.map { it.toInt() }
    }

    fun subReverse(circle: List<Int>, fromInclusive: Int, length: Int): List<Int> {
        val input = circle.toMutableList()
        val mod = input.size

        val switchIterations = length / 2
        var start = fromInclusive
        var end = (fromInclusive+length-1) % mod

        for(i in 0 until switchIterations) {
            val tmp = input[start]
            input[start] = input[end]
            input[end] = tmp
            start = (start+1) % mod
            end = (end-1+mod) % mod
        }
        return input.toList()
    }

    override fun part1(input: List<Int>): Any {
        var circle = (0..255).toList()

        var skip = 0
        var currentPos = 0
        input.forEach { length ->
            circle = subReverse(circle, currentPos, length)
            currentPos = (currentPos+length+skip) % circle.size
            skip++
        }
        return circle[0]*circle[1]
    }

    override fun part2(input: List<Int>): Any {
        val modifiedInput = input.toMutableList()
        modifiedInput.addAll(listOf(17, 31, 73, 47, 23))


        var circle = (0..255).toList()

        var skip = 0
        var currentPos = 0
        for (i in 0 until 64) {
            modifiedInput.forEach { length ->
                circle = subReverse(circle, currentPos, length)
                currentPos = (currentPos+length+skip) % circle.size
                skip++
            }
        }


        return circle.chunked(16)
                .map {
                    it.reduce {acc, i -> acc xor i }
                }.map(Int::toHexString)
                .joinToString("")
    }
}

fun Int.toHexString(): String {
    return this.toString(16).padStart(2,'0')
}