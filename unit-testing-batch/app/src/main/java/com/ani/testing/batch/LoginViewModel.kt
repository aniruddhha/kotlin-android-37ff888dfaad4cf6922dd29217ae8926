package com.ani.testing.batch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class LoginViewModel : ViewModel() {

    private val _eml = MutableLiveData<String>()
    val eml : LiveData<String> = _eml

    private val _pas = MutableLiveData<String>()
    val pas : LiveData<String> = _pas

    private val _sts = MutableLiveData<String>()
    val sts : LiveData<String> = _sts

    fun setEml(ch : CharSequence) {
        _eml.value = ch.toString()
    }

    fun setPas(ch : CharSequence) {
        _pas.value = ch.toString()
    }

    fun onLogin() {

        Log.i("@ani", "Email: ${eml.value}, Password ${pas.value}" )
        if(
            Validator.validateEmail(eml.value ?: "") &&
            Validator.validatePassword(pas.value ?: "")
        ) {
            _sts.value = "success"
        }else _sts.value = "fail"
    }
}