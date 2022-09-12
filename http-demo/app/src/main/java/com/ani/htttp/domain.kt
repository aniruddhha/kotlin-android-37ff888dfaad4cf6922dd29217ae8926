package com.ani.htttp

import com.google.gson.annotations.SerializedName

data class AppResponse(
    @SerializedName("data") var data: Data = Data(),
    @SerializedName("support") var support: Support = Support()
)

data class Data(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("avatar") var avatar: String? = null
)

data class Support(
    @SerializedName("url") var url: String? = null,
    @SerializedName("text") var text: String? = null
)

data class AppUser(
    val email : String,
    val password : String
)

data class ResponseCreateUser(
    val id : Int,
    val token : String
)