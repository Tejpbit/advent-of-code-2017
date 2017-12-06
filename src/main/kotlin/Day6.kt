class Day6: Day<MutableList<Int>> {
    override fun parse(input: String): MutableList<Int> {
        return input.split("\\s".toRegex()).map(String::toInt).toMutableList()
    }

    fun redistribute(banks: MutableList<Int>) {
        var largest = banks.max()
        var largestIndex = banks.indexOf(largest)
        banks[largestIndex] = 0

        while (largest!! > 0) {
            largestIndex = largestIndex.plus(1).rem(banks.size)
            banks[largestIndex]++
            largest--
        }
    }

    override fun part1(input: MutableList<Int>): Any {
        val memory = hashSetOf<List<Int>>()
        var count = 0

        while (true) {
            redistribute(input)
            count++

            if (memory.contains(input.toList())) {
                break
            }
            memory.add(input.toList())
        }

        return count
    }

    override fun part2(input: MutableList<Int>): Any {
        val memory = hashMapOf<List<Int>, Int>()
        var count = 0

        while (true) {
            redistribute(input)
            count++

            if (memory.contains(input.toList())) {
                break
            }
            memory[input.toList()]=count
        }

        return count - memory[input.toList()]!!
    }

}
