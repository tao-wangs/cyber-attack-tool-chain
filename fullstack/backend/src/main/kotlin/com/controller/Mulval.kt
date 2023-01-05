package com.controller

import com.lordcodes.turtle.shellRun
import com.model.AttackGraphOutput
import com.model.MulvalInput
import java.io.File
import java.lang.Exception

class Mulval(private val inputP: MulvalInput, private val outputDir: AttackGraphOutput) : Controller() {

    private var generated = false

    fun generateGraph(): Boolean {
        generated = false
        val path = outputDir.getPath()
        print("Generating attack graph using MulVAL...")

        val workingDir = File(path)
        generated = try {
            val output = shellRun("graph_gen.sh", listOf(inputP.getPath(), "-v", "-p"), workingDir)
            println(output)
            true
        } catch (e: Exception) {
            print("MulVal has encountered an error...")
            println(e.message)
            false
            return false
        }
        notifyObservers()
        println("Done!")
        return true
    }

    fun getGenerated(): Boolean {
        return generated
    }
}