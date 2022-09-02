package com.ani.kotlindemos.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ani.kotlindemos.R
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    fun coroutineDemo() {
        GlobalScope.launch {
            // async logic
            delay(1500)
            println("Global Scope")
            println("===> ${Thread.currentThread().name}")
        }
    }

    fun ownScope() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1800)
            println("In A Dispatcher.IO")
            println("===> ${Thread.currentThread().name}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineDemo()

        ownScope()
    }
}