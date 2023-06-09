package com.controller

import com.lordcodes.turtle.shellRun
import java.io.File
import java.lang.Exception

object Mulval : Controller() {
    private lateinit var inputFile: File
    private lateinit var outputDir: File
    private var generated = false

    fun init(inputFile: File, outputDir: File){
        this.inputFile = inputFile
        this.outputDir = outputDir
    }

    fun generateGraph(): Boolean {
        generated = false
        print("Generating attack graph using MulVAL...")

        generated = try {
            val output = shellRun("graph_gen.sh", listOf(inputFile.path, "-v", "-p"), outputDir)
            println(output)
            notifyObservers()
            true
        } catch (e: Exception) {
            print("MulVal has encountered an error...")
            println(e.message)
            false
            return false
        }
        println("Done!")
        return true
    }

    fun getGenerated(): Boolean {
        return generated
    }
}