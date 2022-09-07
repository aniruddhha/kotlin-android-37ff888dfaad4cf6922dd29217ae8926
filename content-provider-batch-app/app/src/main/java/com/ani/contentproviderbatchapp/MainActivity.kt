package com.ani.contentproviderbatchapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cursor = contentResolver.query(
            Uri.parse("content://com.ani.storagebatchapp.provider"),
            null,
            null,
            null,
            null,
        )

        Log.i("@ani", ""+cursor)

        cursor?.use {
            Log.i("@ani", "Cursor Not Null")
            while(it.moveToNext()) {
                Log.i("@ani", "Got Record")
                val id = it.getInt(0)
                val issue = it.getString(1)
                val status = it.getInt(2)
                Log.i("@ani", "Id $id, Issue $issue, Status $status")
            }
        }

    }
}