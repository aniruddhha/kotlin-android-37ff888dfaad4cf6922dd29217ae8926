package com.ani.dlgnoti

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private fun notification() {
        findViewById<Button>(R.id.button).setOnClickListener {
//            AppDialog().show(supportFragmentManager, "upload")

            val action = Intent(this, NotiActivity::class.java)
            val pi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getActivity(
                    this,
                    0,
                    action,
                    PendingIntent.FLAG_IMMUTABLE
                )
            } else {
                PendingIntent.getActivity(
                    this,
                    0,
                    action,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

            val noti = NotificationCompat.Builder(this, "abc")
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Title")
                .setContentText("Text")
                .setContentInfo("Info")
                .setChannelId("abc")
                .setContentIntent(pi)
                .build()

            val mgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val chl = NotificationChannel("abc", "testing", NotificationManager.IMPORTANCE_DEFAULT)
                mgr.createNotificationChannel(chl)
            }

            mgr.notify(1, noti)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}