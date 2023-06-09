package com.ktor.routes

import com.attackAgent.CustomAttackAgent
import com.attackAgent.TECHNIQUE_EASYNESS_MAP
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.*

import com.beust.klaxon.Klaxon




fun Route.ConfigurableAttackRouting() {

    route("/attack/defaults") {
        get {

            val list = mutableListOf<String>()
            TECHNIQUE_EASYNESS_MAP.forEach {entry ->
                list.add("\"${entry.key}\": ${entry.value}")
            }
            val jsonString = list.joinToString(",","{", "}")
            call.respond(jsonString)
        }
    }

    route("/attack/custom") {
            post {
                val jsonText = call.receiveText().replace("\\", "").replace("\"[", "[").replace("]\"", "]")
                val jsonString = jsonText

                val map = parseJsonMap(jsonString)

                CustomAttackAgent.AGENT = CustomAttackAgent(map)

                call.respond("{}")
            }
        }
}

private data class TechniqueScore(var technique: String, var score: Int)
private data class TechniqueMap(val techniqueMap: ArrayList<ArrayList<String>>)

private fun parseJsonMap(jsonMap: String): Map<String, Int> {
    val klaxon = Klaxon()

    val techniqueMap = klaxon.parse<TechniqueMap>(jsonMap)
    val mapList = techniqueMap!!.techniqueMap

    val map = mutableMapOf<String, Int>()
    for (list in mapList) {
        map.put(list[0], list[1].toInt())
    }

    return map
}

