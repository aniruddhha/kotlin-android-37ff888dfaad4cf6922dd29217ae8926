package com.ani.batch.service

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.*

class MainActivity : AppCompatActivity() {

    private lateinit var lb  : LocalBroadcastManager

    private var runningService : ForegroundService? = null

    private val br = AppReceiver()

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

    private fun workerManager() {
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

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(uploadImagesWorkerRequest.id)
            .observe(this) {
                when (it.state) {
                    WorkInfo.State.RUNNING -> { Log.i("@ani", "Running Worker") }
                    WorkInfo.State.ENQUEUED -> { Log.i("@ani", "Enqueued Worker") }
                    WorkInfo.State.FAILED -> { Log.i("@ani", "Failed Worker") }
                    WorkInfo.State.SUCCEEDED -> { Log.i("@ani", "Success Worker") }
                    else -> Log.i("@ani", "Invalid State")
                }
            }
//        val uploadImagesWorkerRequest = PeriodicWorkRequestBuilder<HeartMessageWorker>(16, TimeUnit.MINUTES).build()
//
//        WorkManager.getInstance(this)
//                .enqueueUniquePeriodicWork(
//                    "abc",
//                    ExistingPeriodicWorkPolicy.KEEP,
//                    uploadImagesWorkerRequest
//                )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filters = IntentFilter()
        filters.addAction("com.ani.working")
//        registerReceiver(br, filters)

        lb = LocalBroadcastManager.getInstance(this)

        lb.registerReceiver(br, filters)

        findViewById<Button>(R.id.button4).setOnClickListener {
            val brInt = Intent("com.ani.working")
            lb.sendBroadcast(brInt)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        super.onDestroy()

        LocalBroadcastManager.getInstance(this).unregisterReceiver(br)
    }
}