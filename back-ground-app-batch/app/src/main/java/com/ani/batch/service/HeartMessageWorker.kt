package com.ani.batch.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class HeartMessageWorker(
    private val ctx : Context,
    private val params : WorkerParameters
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {

        delay(1500)
        setForeground(startInForeground())
        Log.i("@ani", "Executed Worker")
        return Result.success()
    }

    private fun startInForeground() : ForegroundInfo {

        val intent = WorkManager.getInstance(ctx).createCancelPendingIntent(id)

        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        } else "service-channel-123"

        val notification: Notification = NotificationCompat.Builder(ctx, channelId)
            .setOngoing(true)
            .setContentTitle("Title")
            .setContentText("Content")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(R.drawable.ic_launcher_foreground,"Cancel", intent )
            .setTicker("Ticker")
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()

        return ForegroundInfo(120, notification)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() : String {
        val channelId = "service-channel-123"
        val channel = NotificationChannel(
            channelId,
            "foreground-service-channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val service = ctx.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }
}