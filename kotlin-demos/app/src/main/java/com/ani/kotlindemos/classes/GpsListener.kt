package com.ani.kotlindemos.classes

interface GpsListener {
    val accuracy : Boolean
}

val obj = object : GpsListener {
    override val accuracy: Boolean = false
}