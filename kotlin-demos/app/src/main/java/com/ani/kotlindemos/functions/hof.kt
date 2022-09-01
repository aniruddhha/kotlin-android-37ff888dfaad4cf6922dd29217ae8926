package com.ani.kotlindemos.functions

// functional programming


val fn1 = fun() {

}

val fn2 = fun( fn : () -> Unit ) {

}

val fn3 = fun() : (b : Boolean) -> Unit {
    return fun(b : Boolean) {

    }
}

fun main() {

    fn1()

    fn2( fn1)
    fn2( fun() {  } )

    val f = fn3()
    f(false)
    fn3()(false)

    val arr = arrayOf(1, 2, 3)
    arr.forEach(fun(el) {

    })
    arr.forEach {  }
}
