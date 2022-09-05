package com.ani.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _clk = MutableLiveData("")
    val clk : LiveData<String> = _clk

    fun onStClk() {
//        Log.i("@ani", "Start Clicked")
        _clk.value = "start"
    }

    fun onMdClk() {
//        Log.i("@ani", "Center Clicked")
        _clk.value = "mid"
    }

    fun onEdClk() {
//        Log.i("@ani", "End Clicked")
        _clk.value = "end"
    }
}