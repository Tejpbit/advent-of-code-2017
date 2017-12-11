import java.util.function.Predicate

class Day8: Day<List<Day8.Instruction>> {

    private val registers = mutableMapOf<String, Int>()

    override fun part1(input: List<Instruction>): Any {
        input.forEach {
            if (it.condition.test(true)) {
                val current = registers[it.name]!!
                registers[it.name] = it.operation.execute(current, it.change)
            }
        }


        return registers.maxBy { it.value }!!.value
    }

    override fun part2(input: List<Instruction>): Any {
        return max
    }

    enum class Operation{
        INC {
            override fun execute(old: Int, delta: Int): Int {
                return old+delta

            }
        },

        DEC {
            override fun execute(old: Int, delta: Int): Int {
                return old-delta
            }
        };

        abstract fun execute(old: Int, delta: Int): Int
    }

    data class Instruction(val name: String,
                           val operation: Operation,
                           val change: Int, val
                           condition: Predicate<Boolean>)


    var max = 0
    private fun parsePredicate(condName: String, condOp: String, condVal: Int): Predicate<Boolean> {
        return Predicate {
            val regContent = registers[condName]!!
            if (max < regContent) {
                max = regContent
            }
            when (condOp){
                ">" -> regContent > condVal
                "<" -> regContent < condVal
                "==" -> regContent == condVal
                "!=" -> regContent != condVal
                "<=" -> regContent <= condVal
                ">=" -> regContent >= condVal
                else -> {println("Should not happen $condOp"); false}
            }
        }
    }

    private fun parseOP(strop: String): Operation {
        return if (strop == "inc") {
            Operation.INC
        } else {
            Operation.DEC
        }
    }

    private val regex = "(\\w+) (dec|inc) (-?\\d+) if (\\w+) ([^\\s]+) (-?\\d+)".toRegex()
    override fun parse(input: String): List<Day8.Instruction> {
        val a = input.lines().map {
            val groups = regex.matchEntire(it)?.groups
            val name = groups?.get(1)?.value
            val op = groups?.get(2)?.value
            val change = groups?.get(3)?.value?.toInt()
            val conditionalName = groups?.get(4)?.value
            val conditionalOp = groups?.get(5)?.value
            val conditionalVal = groups?.get(6)?.value?.toInt()

            registers[name!!] = 0
            registers[conditionalName!!] = 0

            Instruction(name,
                    parseOP(op!!),
                    change!!,
                    parsePredicate(conditionalName,conditionalOp!!,conditionalVal!!))
        }
        return a.toList()
    }

}
