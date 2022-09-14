package com.ani.industrystandardproject.restapi

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//https://approov.io/tools/static-pinning/

private val  certPinner = CertificatePinner.Builder()
    .add("domain","sha256/public-keys")
    .build()

private val okHttpClient = OkHttpClient.Builder()
    .certificatePinner(certPinner)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("")
    .client(okHttpClient)
    .build()