package com.ani.htttp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReqResApi {

    @GET("/api/users/{id}")
    fun findOne(@Path("id") id : Int) : Call<AppResponse>

    @POST("/api/register")
    fun createNewUser(@Body user : AppUser) : Call<ResponseCreateUser>
}