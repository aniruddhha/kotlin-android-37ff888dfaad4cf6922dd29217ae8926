package com.ani.jetpack

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class StartViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            delay(1500)
            _mob.value = "900865433"
        }
    }

    private val _mob = MutableStateFlow("")
    val mob : StateFlow<String> = _mob

    private val _pass = MutableStateFlow("")
    val pass : StateFlow<String> = _pass

    private val _btn = MutableLiveData("")
    val btn : LiveData<String> = _btn

    fun setMob(ch : CharSequence) {
        _mob.value = ch.toString()
    }

    fun setPass(ch : CharSequence) {
        _pass.value = ch.toString()
    }

    fun onClkOtp() {
        Log.i("@ani", "Mob ${mob.value} Pass ${pass.value}")
        _btn.value = UUID.randomUUID().toString()
    }
}