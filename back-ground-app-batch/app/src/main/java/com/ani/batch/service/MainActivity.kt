package com.ani.batch.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var runningService : ForegroundService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("@ani", "Activity : Connected to Service")

            val binder = service as ForegroundService.LocalBinder
            runningService = binder.getRemoteService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("@ani", "Activity : Disconnected from Service")
        }
    }

    private fun serviceRelated() {
        val intent = Intent(this, ForegroundService::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
//            startService(intent)
            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            stopService(intent)
            unbindService(connection)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {

            findViewById<TextView>(R.id.txt).apply {
                text = "${runningService?.generateNewRandomNumber}"
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uploadImagesWorkerRequest = OneTimeWorkRequestBuilder<HeartMessageWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiresCharging(true)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .build()
        WorkManager.getInstance(this)
            .enqueueUniqueWork(
                "abc",
                ExistingWorkPolicy.REPLACE,
                uploadImagesWorkerRequest
            )


//        val uploadImagesWorkerRequest = PeriodicWorkRequestBuilder<HeartMessageWorker>(16, TimeUnit.MINUTES).build()
//
//        WorkManager.getInstance(this)
//                .enqueueUniquePeriodicWork(
//                    "abc",
//                    ExistingPeriodicWorkPolicy.KEEP,
//                    uploadImagesWorkerRequest
//                )
    }
}