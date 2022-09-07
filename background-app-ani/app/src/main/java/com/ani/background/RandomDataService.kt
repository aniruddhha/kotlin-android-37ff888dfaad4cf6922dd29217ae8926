package com.ani.background

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class RandomDataService : Service() {

    private val binder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("@ani", "Service: Unbounded Successfully")
        return super.onUnbind(intent)
    }

    private val generator = Random()

    val randomNumber get() = generator.nextInt(255)

     inner class LocalBinder : Binder() {
        fun getRunningService() : RandomDataService = this@RandomDataService
    }
}