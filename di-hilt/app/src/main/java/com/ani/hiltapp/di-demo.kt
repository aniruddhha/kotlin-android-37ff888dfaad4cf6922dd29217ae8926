package com.ani.hiltapp

class Tank

class Piston {

    fun setTank(tank : Tank) { // setter based injection

    }
}

class Engine(val piston: Piston) // constructor based di

class Car(val eng : Engine)

fun demo() {
    val piston = Piston()

    val en = Engine(piston)

    var cr = Car(en)
}