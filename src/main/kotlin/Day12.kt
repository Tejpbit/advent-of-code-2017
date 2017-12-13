class Day12: Day<Day12.Graph> {
    val regex = "(\\d+) <-> (.*)".toRegex()

    data class Node(val id: String)
    data class Edge(val id1: String, val id2: String)
    data class Graph(val nodes: MutableMap<String, Node>, val edges: MutableList<Edge>)

    fun Graph.neighbours(id: String): List<Node> {
        val a = this.edges.filter { it.id1 == id || it.id2 == id }
                .map { if (it.id1 == id) this.nodes[it.id2]!! else this.nodes[it.id1]!! }
                .toMutableList()
                .distinctBy { it.id }
        return a
    }

    fun Graph.breadthFirstSearch(startId: String): List<Node> {
        val visited = mutableListOf<Node>()
        var next = mutableListOf(this.nodes[startId]!!)

        while (next.isNotEmpty()) {
            visited.addAll(next)
            val newNext = next.map { this.neighbours(it.id) }
                    .flatten()
                    .filter { node -> !visited.contains(node) }
            next = newNext.toMutableList()

        }
        return visited
    }

    override fun parse(input: String): Graph {
        val graph = Graph(mutableMapOf(), mutableListOf())

        input.lines().map {
            val groups = regex.matchEntire(it)?.groups
            val source  = groups?.get(1)?.value
            val ids = groups?.get(2)?.value?.split(", ")

            graph.nodes[source!!] = (Node(source))
            for (id in ids!!) {
                graph.edges.add(Edge(source, id))
            }
        }

        return graph
    }

    override fun part1(input: Graph): Any {
        return input.breadthFirstSearch("0").count()
    }

    override fun part2(input: Graph): Any {
        val nodes = input.nodes.values.toMutableSet()
        var groupCount = 0

        while (nodes.isNotEmpty()) {
            nodes.removeAll(input.breadthFirstSearch(nodes.first().id))
            groupCount++
        }
        return groupCount


    }

}
