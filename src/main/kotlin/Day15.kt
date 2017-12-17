import java.util.function.Predicate

class Day15: Day<Int> {
    override fun parse(input: String): Int {
        return 0
    }

    data class Generator(val initialValue: Long, val factor: Long, val predicate: Predicate<Long>) {
        var prevValue = initialValue

        fun next(): Long {
            do {
                     prevValue = (prevValue * factor) % 2147483647
            } while ((! predicate.test(prevValue)))
            return prevValue
        }
    }


    override fun part1(input: Int): Any {
        return checkGens(873, 583)
    }

    fun checkGens(aInit: Int, bInit: Int): Int {
        var aPrev = aInit.toLong()
        var bPrev = bInit.toLong()
        val aFactor = 16807
        val bFactor = 48271
        var compCount = 0

        for (i in 1..40_000_000) {
            aPrev = aPrev*aFactor % 2147483647
            bPrev = bPrev*bFactor % 2147483647

            val aComp = aPrev.and(0b00000000000000001111111111111111)
            val bComp = bPrev.and(0b00000000000000001111111111111111)

            if (aComp == bComp) {
                compCount++
            }
        }
        return compCount
    }


    val BIT_FILTER_16 = 0b00000000000000001111111111111111L
    override fun part2(input: Int): Any {
       return checkPart2Gens(873, 583)
    }

    fun checkPart2Gens(startA: Long, startB: Long): Int {
        val genA = Generator(startA, 16807, Predicate { (it%4) == 0L })
        val genB = Generator(startB, 48271, Predicate { (it%8) == 0L })
        var count = 0

        for (i in 1..5_000_000) {
            if (genA.next().and(BIT_FILTER_16) == genB.next().and(BIT_FILTER_16)){
                count++
            }
        }

        return count
    }

}
