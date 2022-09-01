package com.ani.kotlindemos.functions

fun printMsg1(): Unit {} // ?

fun printMsg2(msg: String): Unit {} // ?

fun add(x: Int, y: Int): Int {
    return x + y
}

fun mul(x: Int, y: Int): Int = x * y //inline functions

fun dv(x: Int = 10, y: Double = 10.0) = x / y // default values

fun details(vararg msgs: String) {
    println(msgs.size)
    println(msgs.forEach { println(it) })
}

fun main() {
    dv(10, 5.7) // normal 10 and 5
    dv() // 10 and 10

    add(70, 30)
    add(y=70, x=30) // named parameters

    infix fun Int.plus(str: Int) = str.minus(this) // extention function
//   println(3.plus("abc,"))
//    println(2 plus "abc,")
    println(4.plus(5)) // -1
    println(4.plus(4)) // 0

    println(10.plus(5)) // 5

//
}