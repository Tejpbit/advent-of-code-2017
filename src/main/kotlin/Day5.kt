class Day5: Day<MutableList<Int>>{
    override fun parse(input: String): MutableList<Int> {
        return input.lines().map(String::toInt).toMutableList()
    }

    override fun part1(input: MutableList<Int>): Any {
        return doJumps(input) {it+1}
    }


    override fun part2(input: MutableList<Int>): Any {
        return doJumps(input) { if (it >= 3) {it-1} else it+1 }
    }

    inline fun doJumps(input: MutableList<Int>, body: (Int) -> Int): Int {
        var currentPos = 0
        var jumps = 0

        while ( 0 <= currentPos && currentPos < input.size) {
            val offset = input[currentPos]
            input[currentPos] = body(offset)
            currentPos += offset
            jumps += 1
        }

        return jumps
    }

}
