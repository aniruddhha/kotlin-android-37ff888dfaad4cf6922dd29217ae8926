package com.ani.storagebatchapp

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.room.Room
import com.ani.storagebatchapp.db.TicketDb
import com.ani.storagebatchapp.domain.Ticket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private val scp = CoroutineScope(Dispatchers.IO)

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.i("@ani", result.data.toString() ?: "")
            Log.i("@ani", "" + result.data?.data)
            Log.i("@ani", "Result is ${result.resultCode == Activity.RESULT_OK}")
            result?.let { res ->
                res.data?.data?.let { uri ->
                    val pfd = contentResolver.openFileDescriptor(uri, "w")
                    pfd?.fileDescriptor?.let { fd ->
                        Log.i("@ani", "Reached ")
                        FileOutputStream(fd).use {
                            it.write("hey ho how are you".toByteArray())
                        }
                    }

                    val pfdRd = contentResolver.openFileDescriptor(uri, "r")
                    pfdRd?.fileDescriptor?.let { fd ->
                        val txt = FileInputStream(fd).use {
                            it.bufferedReader().readText()
                        }
                        Log.i("@ani", txt)
                    }
                }
            }
        }

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TicketDb::class.java,
            "ticket-db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = db.ticketRepository()

        findViewById<Button>(R.id.button).setOnClickListener {

            Log.i("@ani", " Path To Private : ${filesDir.path}")

            openFileOutput("abc.txt", MODE_PRIVATE).use {
                it.write("Hi hello are you".toByteArray())
            }

            val str = openFileInput("abc.txt").use { it.bufferedReader().readText() }
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
            createFile()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {

//            scp.launch {
//
//                repo.createNewTicket(
//                    Ticket(1, "OS installation", false)
//                )
//
//                repo.createNewTicket(
//                    Ticket(2, "Wifi not connecting", false)
//                )
//            }

//            scp.launch {
//                repo.findAll().forEach {
//                    Log.i("@ani", "${it.issue}")
//
//                    scp.launch(Dispatchers.Main) {
//                        findViewById<TextView>(R.id.textView).append(it.issue)
//                    }
//                }
//            }

            scp.launch {
                repo.findAllAsync().collect { tickets ->
                    tickets.forEach { Log.i("@ani", it.toString()) }
                }
            }
//
//            scp.launch {
//                repo.deleteTicket(
//                    Ticket(
//                        tid = 1,
//                        issue = "",
//                        status = false
//                    )
//                )
//            }

//

//            scp.launch {
//                val cur = repo.findAllByCursor()
//                Log.i("@ani", ""+cur)
//            }

        }
    }

    private fun createFile() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "tpmt.txt")
        }
        startActivityForResult.launch(intent)
    }
}