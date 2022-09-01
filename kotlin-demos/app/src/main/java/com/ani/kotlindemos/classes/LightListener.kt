package com.ani.kotlindemos.classes

interface LightListener {
    fun onStart() : Unit
}

class LightPanel : LightListener {
    override fun onStart() {
      println("Traditional : On Light On")
    }
}

val ltPnl = object : LightListener {
    override fun onStart() {
        println("Object : On Light On")
    }
}

fun main() {

    val lt1 = LightPanel()
    lt1.onStart()

    ltPnl.onStart()

}