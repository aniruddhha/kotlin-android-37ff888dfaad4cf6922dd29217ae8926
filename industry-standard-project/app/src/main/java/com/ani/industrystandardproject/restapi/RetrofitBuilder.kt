package com.ani.industrystandardproject.restapi

import com.ani.industrystandardproject.organization.api.OrganizationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitConfigModule {
    private val BASE_URL = "https://api.trello.com"

    @Singleton
    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun organizationApi(retrofit: Retrofit): OrganizationApi = retrofit.create(OrganizationApi::class.java)
}
