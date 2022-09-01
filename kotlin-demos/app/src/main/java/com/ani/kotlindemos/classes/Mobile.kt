package com.ani.kotlindemos.classes

data class Mobile (
    private var _number: String,
    private val _cc: Int,
    private val _lt : LightListener
) {
    var number : String
        get() = _number
        set(value) {
            _number = value
        }
}

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

    val mb = Mobile("skvh", 56, object  : LightListener {
        override fun onStart() {
        }
    })

    mb.number = "abc"
    println(mb.number)


//    TouchPanel(89) // primary
    TouchPanel("abc", 1) // first: secondary
//    TouchPanel(67.89) // second: secondary
}