package com.ani.batch.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AppReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("@ani", "#################")
        Log.i("@ani", "Locale Changed")
        Log.i("@ani", "#################")
        Log.i("@ani", "Broadcast : Action ${intent.action}")
    }
}