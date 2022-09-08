package com.ani.batch.service

import android.app.*
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        startServiceInForeground()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startServiceInForeground() {

        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else "service-channel-123"

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setOngoing(true)
            .setContentTitle("Title")
            .setContentText("Content")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(createPendingIntent())
            .setTicker("Ticker")
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()

        startForeground(12, notification)
    }

    private fun createPendingIntent() : PendingIntent  {
        val intent = Intent(this, MainActivity::class.java)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this,
                1284 ,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                1284 ,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() : String {
        val channelId = "service-channel-123"
        val channel = NotificationChannel(
            channelId,
            "foreground-service-channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val service = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }
}