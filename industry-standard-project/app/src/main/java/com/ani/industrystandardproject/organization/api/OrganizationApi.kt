package com.ani.industrystandardproject.organization.api

import com.ani.industrystandardproject.organization.domain.Organization
import com.ani.industrystandardproject.organization.dto.CreateOrganizationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrganizationApi {

    @POST("/1/organizations")
    fun create(
        @Query("displayName") displayName: String,
        @Query("desc") desc : String,
        @Query("key") key: String,
        @Query("token") token: String
    ) : Call<CreateOrganizationResponse>

    @GET("/1/members/me/organizations")
    fun findAll(
        @Query("key") key: String,
        @Query("token") token: String
    ) : Call<List<Organization>>
//
//    fun delete()
//
//    fun update()
//
//    fun findOne()
}