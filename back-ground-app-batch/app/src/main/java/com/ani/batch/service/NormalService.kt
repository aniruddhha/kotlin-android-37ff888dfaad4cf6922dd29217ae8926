package com.ani.batch.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class NormalService : Service() {

    override fun onBind(intent: Intent): IBinder?  {
        Log.i("@ani", "Normal : onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()

        Log.i("@ani", "Normal : onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("@ani", "Normal : onStartCommand")

        return START_NOT_STICKY
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("@ani", "Normal : onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.i("@ani", "Normal : onDestroy")
        super.onDestroy()
    }
}