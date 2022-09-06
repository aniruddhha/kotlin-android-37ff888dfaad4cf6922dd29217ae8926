package com.ani.provider

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private val startActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        Log.i("@ani", "------>"+intent?.data)

       result?.data?.data?.let { it ->
           val fd = contentResolver.openFileDescriptor(it, "r")
            val fis = FileInputStream(fd?.fileDescriptor)
           val txt = fis.bufferedReader().use { rd -> rd.readText() }
           Log.i("@ani", txt)
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      findViewById<Button>(R.id.button).setOnClickListener {

          val intent = Intent(Intent.ACTION_PICK)
          intent.type = "text/plain"
          startActivityForResult.launch(intent)
      }
    }
}