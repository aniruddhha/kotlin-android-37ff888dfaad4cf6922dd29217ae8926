package com.ani.kotlindemos.classes

import android.text.method.Touch

class Mobile(
    val number: String,
    val cc: Int
)

class TouchPanel(val sz : Int) {

    val first = "Number one ${sz}".also(::println)

    init {
        println("init block ${sz}")
    }
    constructor(col : String, s : Int) : this(s) {
        println("first secondary")
    }
    constructor(dbl : Double) : this(10) {
        println("second secondary")
    }
}

fun main() {

//    TouchPanel(89) // primary
    TouchPanel("abc", 1) // first: secondary
//    TouchPanel(67.89) // second: secondary
}