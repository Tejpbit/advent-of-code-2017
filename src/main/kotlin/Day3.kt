import kotlin.math.absoluteValue

class Day3: Day<Map<Day3.Coord, Int>> {

    var target = ""

    val NORTH = Coord(0,1)
    val SOUTH = Coord(0,-1)
    val WEST = Coord(-1,0)
    val EAST = Coord(1,0)

    class Coord(val x: Int, val y: Int) {
        fun move(direction: Coord): Coord {
            return Coord(
                    this.x + direction.x,
                    this.y + direction.y
            )
        }

        fun manhattanDistance(coord: Coord): Int {
            return this.x.minus(coord.x).absoluteValue +
                    this.y.minus(coord.y).absoluteValue
        }

        fun neighbours(): List<Coord> {
            return listOf(
                    Coord(x-1, y-1),
                    Coord(x-1, y),
                    Coord(x-1, y+1),
                    Coord(x, y+1),
                    Coord(x, y-1),
                    Coord(x+1, y-1),
                    Coord(x+1, y),
                    Coord(x+1, y+1)
            )
        }

        fun opposite(): Coord {
            return Coord(-x, -y)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Coord

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }
    }

    override fun parse(input: String): Map<Coord, Int> {
        this.target = input

        return buildSpiral(input.toInt(), ::calculateValue1)
    }

    override fun parse2(input: String): Map<Coord, Int> {
        this.target = input

        return buildSpiral(input.toInt(), ::calculateValue2)
    }

    private fun buildSpiral(target: Int, valueFunction: (m: Map<Coord, Int>, c: Coord, prev: Coord) -> Int): Map<Coord, Int> {
        val m = HashMap<Coord, Int>()
        m[Coord(0,0)] = 1
        var pos = Coord(0,0)
        var nextEast = 1
        var nextNorth = 1
        var nextWest = 2
        var nextSouth = 2


        fun stretch(length: Int, direciton: Coord) {
            for (x in length downTo 1) {
                pos = pos.move(direciton)
                m[pos] = valueFunction(m, pos, direciton.opposite())
            }
        }

        while (true) {
            if (m[pos]!! > target) {
                return m
            }

            stretch(nextEast, EAST)
            nextEast += 2

            stretch(nextNorth, NORTH)
            nextNorth += 2

            stretch(nextWest, WEST)
            nextWest += 2

            stretch(nextSouth, SOUTH)
            nextSouth += 2

        }
    }

    private fun calculateValue1(m: Map<Coord, Int>, c: Coord, movedFrom: Coord): Int {
        val value = m[c.move(movedFrom)]
        return value!! +1
    }

    fun calculateValue2(m: Map<Coord, Int>, c: Coord, prev: Coord): Int {
        val sum = c.neighbours().map { it -> m[it] }.sumBy { it ?: 0 }
        return sum
    }

    override fun part1(input: Map<Coord, Int>): Any {
        val e = input.entries.find { e -> e.value == target.toInt() }
        val coord = e!!.key
        return coord.manhattanDistance(Coord(0,0))
    }

    override fun part2(input: Map<Coord, Int>): Any {
        val e = input.values.sorted().dropWhile { it <= target.toInt()}

        return e[0]
    }

}
