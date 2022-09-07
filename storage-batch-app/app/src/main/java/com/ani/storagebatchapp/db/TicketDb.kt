package com.ani.storagebatchapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ani.storagebatchapp.domain.Ticket
import com.ani.storagebatchapp.repository.TicketRepository

@Database(entities = [Ticket::class], version = 1)
abstract class TicketDb : RoomDatabase() {
     abstract fun ticketRepository() : TicketRepository
}