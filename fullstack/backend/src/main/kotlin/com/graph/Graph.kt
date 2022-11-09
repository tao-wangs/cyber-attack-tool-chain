package com.graph

import com.beust.klaxon.Klaxon
import com.cytoscape.CytoDataWrapper

import com.cytoscape.CytoNode
import com.cytoscape.CytoEdge

interface GraphNode {
    fun id(): Int
}

abstract class Graph<T: GraphNode>(val nodes: MutableMap<Int, T>, val arcs: MutableMap<Int, MutableSet<Int>>) {
    fun getNode(id: Int): GraphNode? {
        return nodes[id]
    }

    fun getArcs(id: Int): List<Int>? {
        return arcs[id]?.toList()
    }

    fun hasArc(id1: Int, id2: Int): Boolean? {
        return arcs[id1]?.contains(id2)
    }
}

/*class Graph(val list : MutableList<NodeOrRelationship>) {

    fun getNode(id: Int) : Node {
        try {
            val node = list.first { nr -> nr.id == id && nr is Node }
            return node as Node
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Could not find Node with id $id")
        }
    }

    fun getRelationship(id: Int) : Relationship {
        try {
            val relationship = list.first { nr -> nr.id == id && nr is Relationship }
            return relationship as Relationship
        } catch (e: NoSuchElementException) {
            throw NoSuchElementException("Could not find Relationship with id $id")
        }
    }

    fun exportToCytoscapeJSON() : String {

        val klaxon = Klaxon()
        val strList: List<String> = list.map { nr -> klaxon.toJsonString(nr.toCytoscapeJson()) }
        return strList.joinToString(prefix = "[", postfix = "]")

    }

    override fun toString(): String {
        return list.toString()
    }
}

abstract class NodeOrRelationship(open val id: Int, open val properties: MutableMap<String, Any>) {
    abstract fun toCytoscapeJson() : CytoDataWrapper
}

data class Node(override val id: Int,
                override val properties: MutableMap<String, Any>,
                val labels: List<String>) : NodeOrRelationship(id, properties) {

    override fun toCytoscapeJson(): CytoDataWrapper {
        val cytoNode = CytoNode("n${id.toString()}", id.toString())
        cytoNode.addProperties(properties)
        return CytoDataWrapper(cytoNode)
    }

    override fun toString(): String {
        return "Node $id: $labels"
    }
}

data class Relationship(override val id: Int,
                        override val properties: MutableMap<String, Any>,
                        val label: String,
                        val startId: Int,
                        val endId: Int) : NodeOrRelationship(id, properties) {

    override fun toCytoscapeJson(): CytoDataWrapper {
        val cytoEdge = CytoEdge("e${id.toString()}", "n${startId.toString()}", "n${endId.toString()}", label)
        cytoEdge.addProperties(properties)
        return CytoDataWrapper(cytoEdge)
    }

    override fun toString(): String {
        return "Relationship $id: $startId $label $endId"
    }
}
*/