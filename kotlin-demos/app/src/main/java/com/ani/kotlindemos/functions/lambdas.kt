package com.ani.kotlindemos.functions

typealias GoodFn = (x: Int, y: Int) -> Int

val pt1: GoodFn = fun(x, y): Int {
    return x + y
}

val pt2: GoodFn = { x, y -> x + y }

fun passPoint(gf: GoodFn) {

}

fun main() {

    passPoint(fun(x, y): Int { return x + y })
    passPoint({ x, y -> x + y })
    passPoint(){ x, y -> x + y }
    passPoint { x, y -> x + y }
}