package com.ani.kotlindemos.classes

import java.time.LocalDate

open class Car(
    private var spd : Int,
    private val dt : LocalDate
) {
    open fun speedUp() {
        spd += 10
    }

    fun speedDn() {
        spd -= 10
    }
}

class Bmw(
    val spd : Int,
    val dt : LocalDate,
    val tms : Boolean
) : Car( spd, dt ) {

    override fun speedUp() {
        super.speedUp()
    }
}

fun main() {

    val bmw = Bmw(
        100,
        LocalDate.of(2020, 1,1),
        true
    );
}