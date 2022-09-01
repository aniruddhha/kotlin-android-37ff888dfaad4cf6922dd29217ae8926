package com.ani.kotlindemos.variables

data class Part(
    val nm : String,
    val id : Int
)

fun main() {
    val pt = Part("abc", 12)

    val name = pt.nm
    val i = pt.id

    val(_,id) = pt
    println(id)

    val arr = arrayOf(1,3)
    val (num1, num2) = arr
    println("Num1 $num1, Num2 $num2")
}