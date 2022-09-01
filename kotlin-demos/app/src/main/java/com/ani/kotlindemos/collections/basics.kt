package com.ani.kotlindemos.collections

fun main() {

    val names = mutableListOf(1, 900, 5, 78, 15)
    names.add(89)
    println(names.joinToString(","))

    val ids = mutableSetOf("abc", "pqr", "abc", "jml", "lmn")
    ids.add("bvt")
    println(ids.joinToString(","))

    val mp = mutableMapOf(
        1 to "abc",
        2 to "pqr",
        3 to "lmn"
    )

    mp.put(5, "uiy")
    mp[6] = "yty"

    for ( (k, v) in mp) {
        println("Key $k Value $v")
    }
    mp.remove(2)

    for ( (k, v) in mp) {
        println("Key $k Value $v")
    }

//    mp.forEach { k, v -> println("Key $k Value $v")  }
}