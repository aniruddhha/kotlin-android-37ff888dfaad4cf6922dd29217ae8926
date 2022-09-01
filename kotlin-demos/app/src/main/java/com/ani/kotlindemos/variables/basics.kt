package com.ani.kotlindemos.variables


val name: String? = null

var ver: Int = 12
val fn: () -> Unit = fun() { }
//val nth = name ?:  throw RuntimeException("Bad Data") // elvis operator
//val nth1 = if(name != null) name else throw RuntimeException("Bad Data")

fun main() {
//    name = "pqr"
    ver = 90

    name ?: "anc" // elvis
    name?.toString() // null safety
    name!!.toString() // assertion
//    println(nth)
}