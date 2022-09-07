package com.ani.storagebatchapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {

            Log.i("@ani", " Path To Private : ${filesDir.path}")

            openFileOutput("abc.txt", MODE_PRIVATE).use {
                it.write("Hi hello are you".toByteArray())
            }

            val str = openFileInput("abc.txt").use {  it.bufferedReader().readText()  }
            Log.i("@ani", str)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {

            Log.i("@ani", " Path To Public : ${getExternalFilesDir("Photos")}")

            val file = File(getExternalFilesDir("Photos"), "abc.txt")
            FileOutputStream(file).use {
                it.write("Hi hello are you".toByteArray())
            }

            val str = FileInputStream(file).use {
                it.bufferedReader().readText()
            }
            Log.i("@ani", str)
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            val prefs = getSharedPreferences("app_prfs", MODE_PRIVATE)
            prefs.edit()
                .putBoolean("is_update", true)
                .putString("inst_dt", "2022-01-01")
                .apply()
        }

        findViewById<Button>(R.id.button5).setOnClickListener {

            val resolver = applicationContext.contentResolver

            val uri = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

            val metaDt = ContentValues().apply {
                put(MediaStore.Files.FileColumns.DISPLAY_NAME, "abx.txt")
            }

            val savedUri = resolver.insert(uri, metaDt)
            Log.i("@ani", "${savedUri.toString()}")

            val str = savedUri?.let { ur ->
                resolver.openInputStream(ur).use { it?.bufferedReader()?.readText() }
            }
            Log.i("@ani", str!!)
        }
    }
}