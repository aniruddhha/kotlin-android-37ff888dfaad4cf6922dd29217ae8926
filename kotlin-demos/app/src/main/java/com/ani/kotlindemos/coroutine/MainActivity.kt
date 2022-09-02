package com.ani.kotlindemos.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ani.kotlindemos.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    val client = null
    val handler = CoroutineExceptionHandler { _, throwable -> println(throwable.message) }

    fun chats(): Flow<Int> = flow {
        for (i in 1..20) {
            delay(1200)
            emit(i)
        }
    }

    suspend fun networkCall() {
        val url = URL("https://www.google.com/")
        with(url) {
            readText()
        }
    }

    fun coroutineDemo() {

        GlobalScope.launch {
            // async logic
            delay(1500)
            println("Global Scope")
            println("===> ${Thread.currentThread().name}")
        }
    }

    fun ownScope() {
        val scp = CoroutineScope(Dispatchers.Main)
        scp.launch(handler) {
            delay(1800)
            println("In A Dispatcher.IO")
            println("===> ${Thread.currentThread().name}")

            networkCall()
        }
    }

    fun cancellable() {
        val scp = CoroutineScope(Dispatchers.IO)
        val job: Job = scp.launch(handler) {
            println("Started Job")
            delay(1500)
            println("Completed Job")
        }

        job.cancel()
    }

    fun manyTasks() {
        val scp = CoroutineScope(Dispatchers.IO)
        scp.launch {
            val job = launch {

                val task1 = launch {
                    println("Task1 Started")
                    delay(2000)
                    println("Task1 Completed")
                }

                val task2 = async {
                    println("Task2 Started")
                    delay(1200)
                    println("Task2 Completed")

//                task1.cancel()
                    return@async true
                }

                println(task2.await())
            }

//            job.join()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manyTasks()

        CoroutineScope(Dispatchers.IO).launch {
            chats()
                .catch { err -> println(err) }
                .onEach { el -> println("Logging it $el") }
                .map { el -> el * el }
                .collect { println(it) }
        }
    }
}