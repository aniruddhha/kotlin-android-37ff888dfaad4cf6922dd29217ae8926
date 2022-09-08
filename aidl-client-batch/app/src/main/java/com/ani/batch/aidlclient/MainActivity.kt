package com.ani.batch.aidlclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.ani.batch.service.IRemoteRandomGenerator

class MainActivity : AppCompatActivity() {

    private var aidlService : IRemoteRandomGenerator? = null

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            aidlService = IRemoteRandomGenerator.Stub.asInterface(service)
            Log.i("@ani", "Client: Connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            aidlService = null
            Log.i("@ani", "Client: Disconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent("com.ani.batch.service.REMOTE_SERVICE")
        intent.setClassName("com.ani.batch.service", "com.ani.batch.service.RemoteRandomService")

        findViewById<Button>(R.id.btnBind).setOnClickListener {
            bindService(intent, connection, BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btnRnd).setOnClickListener {
            findViewById<TextView>(R.id.txt).apply {
                text = "${aidlService?.randomNumber()}"
            }
        }
    }
}