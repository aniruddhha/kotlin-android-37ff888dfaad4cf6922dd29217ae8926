package com.ani.testing.batch

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id : Int,
    val email : String,
    val password : String
)