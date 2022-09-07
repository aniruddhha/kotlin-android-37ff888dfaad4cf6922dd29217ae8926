package com.ani.storagebatchapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ticket(
    @PrimaryKey
    val tid : Int,

    @ColumnInfo(name = "issue_msg")
    val issue : String,

    @ColumnInfo(name = "status")
    val status : Boolean
)