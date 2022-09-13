package com.ani.testing.batch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao  {

    @Insert
    fun createNewUser(user:  User)

    @Query("select *  from user")
    fun findAll() : List<User>

    @Query("select *  from user where id = :id")
    fun findById( id : Int) : User
}