package com.ani.industrystandardproject.organization.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ani.industrystandardproject.organization.api.OrganizationApi
import com.ani.industrystandardproject.organization.dto.CreateOrganizationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateOrganizationViewModel
@Inject
constructor(
    private val api : OrganizationApi
) : ViewModel() {

    private val _dispNm = MutableLiveData<String>()
    val dispNm : LiveData<String> = _dispNm

    private val _desc = MutableLiveData<String>()
    val desc : LiveData<String> = _desc

    private val _btn = MutableLiveData<String>()
    val btn : LiveData<String> = _btn

    fun setDispNm(ch : CharSequence) {
      _dispNm.value = ch.toString()
    }

    fun setDesc(ch : CharSequence) {
        _desc.value = ch.toString()
    }

    private fun clear() {
        _desc.value = ""
        _dispNm.value = ""
    }

    fun onCreate() {
        Log.i("@ani", "##### Clicked #####")
        Log.i("@ani", "Display Name ${dispNm.value}")
        Log.i("@ani", "Description ${desc.value}")

        api.create(
            dispNm.value ?: throw RuntimeException("Invalid Display Name"),
            desc.value ?: throw RuntimeException("Invalid Description"),
            "",
            ""
        ).enqueue(object : Callback<CreateOrganizationResponse> {
            override fun onResponse(
                call: Call<CreateOrganizationResponse>,
                response: Response<CreateOrganizationResponse>
            ) {
                Log.i("@ani", "##### REST Success #####")
                Log.i("@ani", "${response.body().toString()}")
                clear()
                _btn.value = UUID.randomUUID().toString()
            }

            override fun onFailure(call: Call<CreateOrganizationResponse>, t: Throwable) {
                Log.i("@ani", "##### REST Fail #####")
            }
        })
    }
}