package com.ani.htttp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val SERVER_URL = "https://reqres.in"

    private fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : ReqResApi = retrofit().create(ReqResApi::class.java)
}