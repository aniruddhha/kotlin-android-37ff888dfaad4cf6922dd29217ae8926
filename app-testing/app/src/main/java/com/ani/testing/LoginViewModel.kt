package com.ani.testing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

open class LoginViewModel : ViewModel() {

    private val _lgn = MutableLiveData<String>()
    val lgn : LiveData<String> = _lgn

    private val _em = MutableLiveData<String>()
    val em : LiveData<String> = _em

    private val _ps = MutableLiveData<String>()
    val ps: LiveData<String> = _ps

    private val _sts = MutableLiveData<String>()
    val sts : LiveData<String> = _sts

    fun onEm(eml : CharSequence) {
        _em.value = eml.toString()
    }

    fun onPs(pas : CharSequence) {
        _ps.value = pas.toString()
    }

    fun onLgn() {
        _lgn.value = UUID.randomUUID().toString()
        Log.i("@ani","${em.value}, ${ps.value}" )
        _sts.value = if(
            em.value.equals("abc") && ps.value.equals("abc")
        ) "success" else "fail"
    }
}