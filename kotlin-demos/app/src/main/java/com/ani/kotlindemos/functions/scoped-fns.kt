package com.ani.kotlindemos.functions

import java.time.LocalDate

/*
*  let, run, with, apply, also
* */


data class Charger(
    var port : Int = 10,
    var host: String = "abc"
)

fun letDemo(dt : String?) {
    val ln = dt?.let {
        println(it.isEmpty())
        println(it.length)
        return@let it.length
    }
}

fun runDemo(dt : String?) {
    dt?.run {
        println(isEmpty())
        println(length)
        return@run length
    }
}

fun withDemo() {
    val ch = Charger()

    with(ch) {
        println("$port and $host")
    }

    println("${ch.port} and $ch.host")
}

fun applyDemo() {

    val ch = Charger()
    val newCh = ch.apply {
        port = 9090
        host = "pqr"
    }
}

fun alsoDemo() {
    val ch = Charger()
    val newCh = ch.also {
        it.port = 12
        it.host = "lmb"

        runDemo(it.host)
    }
}

fun main() {

}