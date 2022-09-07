package com.ani.storagebatchapp.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ani.storagebatchapp.domain.Ticket

@Dao
interface TicketRepository {

    @Insert
    fun createNewTicket(ticket : Ticket)

    @Query("select * from ticket")
    fun findAll() : List<Ticket>
}