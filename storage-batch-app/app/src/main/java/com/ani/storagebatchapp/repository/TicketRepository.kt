package com.ani.storagebatchapp.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ani.storagebatchapp.domain.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketRepository {

    @Insert
    fun createNewTicket(ticket : Ticket)

    @Query("select * from ticket")
    fun findAll() : List<Ticket>

    @Query("select * from ticket")
    fun findAllAsync() : Flow<List<Ticket>>

    @Delete
    suspend fun deleteTicket(ticket: Ticket)
}