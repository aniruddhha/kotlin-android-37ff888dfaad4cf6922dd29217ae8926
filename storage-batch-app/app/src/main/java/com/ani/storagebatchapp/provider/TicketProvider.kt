package com.ani.storagebatchapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.room.Room
import com.ani.storagebatchapp.db.TicketDb
import com.ani.storagebatchapp.repository.TicketRepository

class TicketProvider : ContentProvider() {

    private lateinit var db : TicketDb
    private lateinit var repository: TicketRepository

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {

        context?.let {
            db = Room.databaseBuilder(
                it,
                TicketDb::class.java,
                "ticket-db"
            ).build()

            Log.i("@ani", "Db Initilized ")
            repository = db.ticketRepository()
            Log.i("@ani", "Repo Initilized ")
        }

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor {
        Log.i("@ani", "Called for query")
        Log.i("@ani", uri.toString())
        return repository.findAllByCursor()
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}