package com.ani.kotlindemos

class Tester {

    lateinit var source : String

    val isConnected : Boolean by lazy { true }

    fun exposeData() {
        source = "abc"

        isConnected
    }
}