package com.ani.kotlindemos.loops

fun whenDemo() {

    val day = 23

    val str = when(day) {
        in 1..20 -> "Mon"
        22, 23 -> "Tue $day"
        else -> "Sun"
    }

    println(str)

}


fun main() {
    whenDemo()
}