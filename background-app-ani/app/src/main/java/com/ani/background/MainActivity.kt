package com.ani.background

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var randomDataService: RandomDataService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RandomDataService.LocalBinder
            randomDataService = binder.getRunningService()
            isBound = true
            Log.i("@ani", "Bound Successfully")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            randomDataService = null
            Log.i("@ani", "Unbound Successfully")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent(this,DataProviderService::class.java).also {
            startService(it)
        }

        val intent = Intent(this, RandomDataService::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
//            startService(intent)
            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            unbindService(connection)
            stopService(intent)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            if(isBound) {
                Log.i("@ani", "Number ${ randomDataService?.randomNumber}")
            }
        }
    }
}