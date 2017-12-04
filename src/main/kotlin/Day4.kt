class Day4: Day<List<List<String>>> {
    override fun parse(input: String): List<List<String>> {
        return input.lines().map { it -> it.split("\\s".toRegex()) }
    }

    override fun part1(input: List<List<String>>): Any {
        return input.map { it -> isValid(it) }.count { it }
    }

    fun isValid(passphrase: List<*>): Boolean {
        return passphrase.toSet().size == passphrase.size
    }

    override fun part2(input: List<List<String>>): Any {
        val sortedLists = input.map { it -> it.map { it -> it.toList().sorted() } }
        return sortedLists.map { it -> isValid(it) }.count {it}

    }

}
