package com.ani.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {

    private fun simple() : Flow<Int> {
        return flow {
            for(i in 0..10) {
                delay(1000)
                emit(i)
            }
        }
    }


    private suspend fun one() {
        delay(1000)
        println("In One")
    }

    private suspend fun two() {
        delay(1000)
        println("In Two")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Unconfined).launch {
            val on = launch {  one() }
            val tw = launch { two() }
        }
        println("Completely Executed")
    }
}