class Day6: Day<MutableList<Int>> {
    override fun parse(input: String): MutableList<Int> {
        return input.split("\\s".toRegex()).map(String::toInt).toMutableList()
    }

    fun redistribute(banks: MutableList<Int>) {
        var largestIndex = banks.indices.maxBy { banks[it] } ?: -1
        var largest = banks[largestIndex]
        banks[largestIndex] = 0

        while (largest > 0) {
            largestIndex = largestIndex.plus(1).rem(banks.size)
            banks[largestIndex]++
            largest--
        }
    }

    override fun part1(input: MutableList<Int>): Any {
        val memory = hashSetOf<MutableList<Int>>()
        var count = 0

        while (true) {
            redistribute(input)
            count++

            if (memory.contains(input.toList())) {
                break
            }
            memory.add(input)
        }

        return count
    }

    override fun part2(input: MutableList<Int>): Any {
        val memory = hashMapOf<MutableList<Int>, Int>()
        var count = 0

        while (true) {
            redistribute(input)
            count++

            if (memory.contains(input.toList())) {
                break
            }
            memory[input]=count
        }

        return count - memory[input]!!
    }

}
