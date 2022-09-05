package com.ani.jetpack

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    fun onStClk() {
        Log.i("@ani", "Start Clicked")
    }

    fun onMdClk() {
        Log.i("@ani", "Center Clicked")
    }

    fun onEdClk() {
        Log.i("@ani", "End Clicked")
    }
}