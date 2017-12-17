import Day12.Graph
import Day12.Node
import Day12.Edge

class Day14: Day<String> {
    override fun parse(input: String): String {
        return input
    }

    override fun part1(input: String): Any {

        return createBinaryGrid(input)
                .map{it.asSequence().count { it == '1' }}
                .sum()

    }

    fun createBinaryGrid(input: String): List<String> {
        val knotHasher = Day10()
        return (0..127).map {
            knotHasher.part2(knotHasher.parse2("$input-$it")) as String
        }.map(String::hexToBinary)

    }

    override fun part2(input: String): Any {

        val graph = createGraph(input)

        val d12 = Day12()
        return d12.part2(graph)
    }


    fun createGraph(input: String): Day12.Graph {
        val binGrid = createBinaryGrid(input)

        return graphFromBinGrid(binGrid)
    }

    fun graphFromBinGrid(binGrid: List<String>): Graph {
        val grid = binGrid.map { it.chunked(1) }
        val graph = Graph()



        grid.forEachIndexed { y, col ->
            col.forEachIndexed{ x, cell ->
                if (cell == "1") {

                    val nodeName = "$x,$y"
                    graph.setNode(Node(nodeName))

                    if (x+1 < grid[y].size && grid[y][x+1] == "1") {
                        graph.addEdge(
                                Edge(Node(nodeName), Node("${x+1},$y"))
                        )
                    }
                    if (y+1 < grid.size && grid[y+1][x] == "1") {
                        graph.addEdge(
                                Edge(Node(nodeName), Node("$x,${y+1}"))
                        )
                    }

                }
            }
        }
        return graph
    }

}

fun String.hexToBinary(): String {

    return this.chunked(1).map {
        it.toInt(16).toString(2).padStart(4, '0')
    }.joinToString("")
}