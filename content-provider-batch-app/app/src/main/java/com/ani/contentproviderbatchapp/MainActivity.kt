package com.ani.contentproviderbatchapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val cursor = contentResolver.query(
//            Uri.parse("content://com.ani.storagebatchapp.provider"),
//            null,
//            null,
//            null,
//            null,
//        )
//
//        Log.i("@ani", ""+cursor)
//
//        cursor?.use {
//            Log.i("@ani", "Cursor Not Null")
//            while(it.moveToNext()) {
//                Log.i("@ani", "Got Record")
//                val id = it.getInt(0)
//                val issue = it.getString(1)
//                val status = it.getInt(2)
//                Log.i("@ani", "Id $id, Issue $issue, Status $status")
//            }
//        }

        val fields = listOf<String>(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        ).toTypedArray()

        val contactCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            fields,
            null,
            null,
            null
        )

        contactCursor?.use {
            while(it.moveToNext()) {
                val name = it.getString(0)
                val num = it.getString(1)
                Log.i("@ani", "Name $name, Num $num")
            }
        }
    }
}