package com.ani.htttp

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FcmService : FirebaseMessagingService() {

    private lateinit var brMgr : LocalBroadcastManager
    override fun onCreate() {
        super.onCreate()

        brMgr = LocalBroadcastManager.getInstance(this)
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("@ani", "Service Token : $token" )
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.i("@ani", "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.i("@ani", "Message data payload: ${remoteMessage.data}")
        }

        remoteMessage.notification?.let { remoteMsg ->
            Log.d("@ani", "Message Notification Body: ${remoteMsg.body}")
//            brMgr.sendBroadcast(
//                Intent().also {
//                    it.action = "com.ani.fcm"
//                    it.putExtra("body", remoteMsg.body)
//                }
//            )

            FcmEventBus.scp.launch {
                FcmEventBus.bus.emit(remoteMsg.body ?: "no body")
            }
        }
    }
}