package com.ani.kotlindemos.loops

fun forVariations() {
    val nums = intArrayOf(90, 78, 56, 12, 3, 8, 9)

//    nums.map { x -> x-5 }.forEach(::println)
//    nums.filter { x -> x > 40 }.forEach(::println)

//    for ( i in nums) {
//        println(i)
//    }

//    for (i in 1..90) {
//        println(i)
//    }

//    for ( i in 1 until 90) {
//        println(i)
//    }

//    for ( i in 1 until 90 step 3) {
//        println(i)
//    }

    for (i in 10 downTo 1) {
        println(i)
    }
}

fun whileVariations() {
    var i = 10;
    while (i >= 1) {
        i--
        println(i)
    }

    var j = 10
    do {
        j--
        println(j);
    } while (j >= 1)
}

fun main() {
    whileVariations()
}