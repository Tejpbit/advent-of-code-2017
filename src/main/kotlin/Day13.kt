class Day13: Day<List<Day13.Layer>> {

    data class Layer(val depth: Int, val length: Int) {
        fun severity(): Int {
            return depth*length
        }
    }

    override fun parse(input: String): List<Layer> {
        return input.lines().map {
            val s = it.split(": ")
            Layer(s[0].trim().toInt(), s[1].trim().toInt())
        }
    }

    override fun part1(input: List<Layer>): Any {
        var severityCount = 0

        input.forEach { layer ->
            val upDownSteps = (layer.length-1)*2
            if (layer.depth % upDownSteps == 0 ) {
                severityCount += layer.severity()
            }
        }

        return severityCount
    }

    override fun part2(input: List<Layer>): Any {
        var offset = -1

        loop@while (true) {
            offset++

            for (layer in input) {
                val upDownSteps = (layer.length-1)*2
                if ((offset+layer.depth) % upDownSteps == 0 ) {
                    continue@loop
                }
            }
            break
        }
        
        return offset
    }

}
