package com.ani.storage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            Log.i("@ani", "Path ${filesDir.path}")

            openFileOutput("abc.txt", MODE_PRIVATE).use {
                it.write("Hey Hi, I am okay".toByteArray())
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            val path = FileProvider.getUriForFile(
                    this,
                    "com.ani.storage.fileprovider",
                    File(filesDir, "abc.txt")
                )

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This is one image I'm sharing.")
            shareIntent.putExtra(Intent.EXTRA_STREAM, path)
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share..."))
        }
    }
}
