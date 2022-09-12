package com.ani.htttp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow

object FcmEventBus {

    val scp : CoroutineScope = CoroutineScope(Dispatchers.Main)

    val bus : MutableSharedFlow<String> by lazy {
        MutableSharedFlow<String>()
    }
}