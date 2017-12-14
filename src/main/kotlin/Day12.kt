class Day12: Day<Day12.Graph> {
    val regex = "(\\d+) <-> (.*)".toRegex()

    data class Node(val id: String)
    data class Edge(val from: Node, val to: Node)
    data class Graph(
            private var nodes: MutableMap<String, Node>,
            private var edges: MutableMap<Node, MutableList<Node>>
    ){
        constructor() : this(mutableMapOf(), mutableMapOf())

        fun getNodeSet(): Set<Node> {
            return this.nodes.values.toSet()
        }

        fun isEmpty(): Boolean {
            return this.nodes.isEmpty()
        }

        fun setNode(n: Node) {
            this.nodes[n.id] = n
            this.edges[n] = this.edges[n] ?: mutableListOf()
        }

        fun addEdge(e: Edge) {
            this.setNode(e.from)
            this.setNode(e.to)
            this.edges[e.from]?.add(e.to)
            this.edges[e.to]?.add(e.from)

        }

        fun removeNode(n: Node) {
            this.nodes.remove(n.id)
            edges[n]?.removeAll { it.id == n.id }
            this.edges.remove(n)
        }

        fun removeNodes(n: List<Node>) {
            n.forEach { this.removeNode(it) }
        }

        fun neighbours(id: String): List<Node> {
            val node = this.nodes[id]
            return (edges[node] ?: mutableListOf()).toList()
        }

        fun breadthFirstSearch(startId: String): List<Node> {
            //val visited = mutableListOf<Node>()
            val visited = mutableSetOf<Node>()
            var next = mutableListOf(this.nodes[startId]!!)

            while (next.isNotEmpty()) {
                visited.addAll(next)
                val newNext = next.map { this.neighbours(it.id) }
                        .flatten()
                        .filter { node -> !visited.contains(node) }
                next = newNext.toMutableList()

            }
            return visited.toList()
        }
    }


    override fun parse(input: String): Graph {
        val graph = Graph()

        input.lines().map {
            val groups = regex.matchEntire(it)?.groups
            val source  = groups?.get(1)?.value
            val ids = groups?.get(2)?.value?.split(", ")

            graph.setNode(Node(source!!))
            for (id in ids!!) {
                graph.addEdge(Edge(Node(source), Node(id)))
            }
        }

        return graph
    }

    override fun part1(input: Graph): Any {
        return input.breadthFirstSearch("0").count()
    }

    override fun part2(input: Graph): Any {
        var groupCount = 0

        while (! input.isEmpty()) {
            input.removeNodes(
                    input.breadthFirstSearch(input.getNodeSet().first().id)
            )
            groupCount++
        }
        return groupCount


    }

}
