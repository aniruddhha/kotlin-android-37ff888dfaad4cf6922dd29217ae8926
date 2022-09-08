package com.ani.batch.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("@ani", "Activity : Connected to Service")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("@ani", "Activity : Disconnected from Service")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, ForegroundService::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
            startService(intent)
//            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            stopService(intent)
//            unbindService(connection)
        }
    }
}