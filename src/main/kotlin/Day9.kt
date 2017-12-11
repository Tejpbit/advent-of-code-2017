class Day9: Day<String> {



    override fun parse(input: String): String {
        return input
    }

    override fun part1(input: String): Any {
        var depth = 0
        var sum = 0
        var skipCount = 0
        var garbageMode = false

        for (it: Char in input) {
            when {
                skipCount > 0 -> skipCount--
                it=='!' -> skipCount++
                it=='<' -> garbageMode = true
                it=='>' -> garbageMode = false
                garbageMode -> 0 //NO-OPERATION
                it=='{' -> depth++
                it=='}' -> {
                    sum += depth
                    depth--
                }
            }
        }

        return sum
    }

    override fun part2(input: String): Any {
        var depth = 0
        var sum = 0
        var skipCount = 0
        var garbageMode = false
        var trashCount = 0

        for (it: Char in input) {
            when {
                skipCount > 0 -> skipCount--
                it=='!'       -> skipCount++
                it=='>'       -> garbageMode = false
                garbageMode   -> trashCount++
                it=='<'       -> garbageMode = true
                it=='{'       -> depth++
                it=='}'        -> {
                    sum += depth
                    depth--
                }
            }
        }

        return trashCount
    }


}
