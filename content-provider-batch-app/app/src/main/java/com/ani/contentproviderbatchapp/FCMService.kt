package com.ani.contentproviderbatchapp

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("@ani", "From: ${remoteMessage.from}")


        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("@ani", "Message data payload: ${remoteMessage.data}")
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d("@ani", "Message Notification Body: ${it.body}")
        }
    }

}