import Day3.Coord

class Day19: Day<Array<CharArray>> {
    companion object {
        val NORTH = Coord(0,-1)
        val SOUTH = Coord(0,1)
        val WEST = Coord(-1,0)
        val EAST = Coord(1,0)
    }

    override fun parse(input: String): Array<CharArray> {
        return input.lines().toMutableList()
                .map { it.toCharArray() }.toTypedArray()



    }

    fun Coord.plusNeighbours(): List<Coord> {
        return listOf(
                Coord(x+1, y),
                Coord(x-1, y),
                Coord(x, y+1),
                Coord(x, y-1)
        )
    }

    fun Coord.neighboursExcept(direction: Coord): List<Coord> {
        val excludedCoord = this.move(direction)
        val neighbours = this.plusNeighbours().toMutableList()
        neighbours.remove(excludedCoord)
        return neighbours.toList()
    }

    fun Coord.directionOf(coord: Coord): Coord {
        return when (coord) {
            this.move(Day19.NORTH) -> NORTH
            this.move(Day19.SOUTH) -> SOUTH
            this.move(Day19.EAST) -> EAST
            this.move(Day19.WEST) -> WEST
            else -> throw Exception("Bad direction of")
        }
    }

    fun NOOP() {}

    override fun part1(input: Array<CharArray>): Any {
        val grid = input

        var position = Coord(0,0)
        var direction = SOUTH
        val letters = mutableListOf<Char>()
        // Find start
        for ((i, ch) in grid[0].withIndex()) {
            if (ch == '|') {
                position = Coord(i, 0)
            }
        }

        forever@while(true) {

            val charAtPos = grid[position.y][position.x]

            when (charAtPos) {
                //End of the line
                ' ' -> break@forever
                // Find new direction
                '+' -> direction = position.directionOf(
                        position.neighboursExcept(direction.opposite())
                                .first {
                                    0 <= it.y && it.y < grid.size &&
                                    0 <= it.x && it.x < grid[it.y].size &&
                                    !grid[it.y][it.x].isWhitespace()
                                })
                //
                '|' -> NOOP()
                //
                '-' -> NOOP()
                //Record letter
                else -> letters.add(charAtPos)

            }

            //Move, continue in same direction until '+' is found.
            position = position.move(direction)
        }




        return letters.joinToString("")
    }

    override fun part2(input: Array<CharArray>): Any {
        val grid = input

        var position = Coord(0,0)
        var direction = SOUTH
        val letters = mutableListOf<Char>()
        var steps = 0

        // Find start
        for ((i, ch) in grid[0].withIndex()) {
            if (ch == '|') {
                position = Coord(i, 0)
            }
        }


        forever@while(true) {

            val charAtPos = grid[position.y][position.x]

            when (charAtPos) {
            //End of the line
                ' ' -> break@forever
            // Find new direction
                '+' -> direction = position.directionOf(
                        position.neighboursExcept(direction.opposite())
                                .first {
                                    0 <= it.y && it.y < grid.size &&
                                            0 <= it.x && it.x < grid[it.y].size &&
                                            !grid[it.y][it.x].isWhitespace()
                                })
            //
                '|' -> NOOP()
            //
                '-' -> NOOP()
            //Record letter
                else -> letters.add(charAtPos)

            }

            //Move, continue in same direction until '+' is found.
            position = position.move(direction)
            steps++
        }

        return steps
    }
}
