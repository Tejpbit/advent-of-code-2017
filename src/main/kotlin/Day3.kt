import kotlin.math.absoluteValue

class Day3: Day<List<List<Int>>> {

    private var input = ""

    override fun parse(input: String): List<List<Int>> {
        this.input = input

        val targetValue = input.toInt()
        val grid = mutableListOf(mutableListOf(1))
        while (true) {
            var prev = grid.last().last()

            if (prev >= targetValue) {
                break
            }

            val beginIndex = grid.size - 1
            val prevGridWidth = grid.size



            //append to previous rows
            grid.reversed().forEach { it.add(prev+1); prev++ }

            //New top row
            val topList = (grid.last().size + prev + 1 downTo prev + 1).toMutableList()
            prev += topList.size

            //add head to previous rows
            grid.forEach { it.add(0, prev+1); prev++ }

            val bottomList = (prev+1..prev+prevGridWidth+2).toMutableList()

            grid.add(0, topList)
            grid.add(bottomList)
        }

        return grid
    }

    override fun part1(input: List<List<Int>>): Any {
        val target = this.input.toInt()

        val yIndex = findYIndex(input, target)
        val xIndex = input[yIndex].indexOf(target)

        val yIndexFor1 = input.size/2
        val xIndexFor1 = input.size/2


        val yDiff = yIndex.minus(yIndexFor1).absoluteValue
        val xDiff = xIndex.minus(xIndexFor1).absoluteValue


        return yDiff+xDiff
    }

    fun findYIndex(input: List<List<Int>>, target: Int): Int {
        return input.indexOf( input.find { x -> x.contains(target) } )
    }

    override fun part2(input: List<List<Int>>): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
