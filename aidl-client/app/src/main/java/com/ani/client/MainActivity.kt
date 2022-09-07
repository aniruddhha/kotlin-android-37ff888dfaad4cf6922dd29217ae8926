package com.ani.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ani.background.IDataProvider

class MainActivity : AppCompatActivity() {

    private var aidlService : IDataProvider? = null
//
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("@ani", "client: Connected")
            aidlService = IDataProvider.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("@ani", "client: Disconnected")
            aidlService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent()
            intent.action = "com.ani.background.SERVICE"
            intent.setClassName("com.ani.background", "com.ani.background.DataProviderService")
            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val txt = findViewById<TextView>(R.id.txt)
            txt.text = "${aidlService?.provideData("hi")}" ?: "default"
        }
    }
}