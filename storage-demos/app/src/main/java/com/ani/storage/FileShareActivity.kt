package com.ani.storage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.FileProvider
import java.io.File

class FileShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_share)

        findViewById<Button>(R.id.button3).setOnClickListener {
            val uri = FileProvider.getUriForFile(
                this,
                "com.ani.storage.fileprovider",
                File(filesDir, "abc.txt")
            )
            val shareIntent = Intent()
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            shareIntent.setDataAndType(uri, contentResolver.getType(uri))
            setResult(Activity.RESULT_OK, shareIntent)
            finish()
        }
    }
}
