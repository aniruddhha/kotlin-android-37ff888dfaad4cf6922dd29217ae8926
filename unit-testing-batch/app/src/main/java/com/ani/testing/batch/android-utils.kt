package com.ani.testing.batch

import android.os.Bundle

object AndroidUtils {
    fun mapToBundle(map : Map<String?, String>): Bundle?  {
        if(map.containsKey(null)) return null
        val bnd = Bundle()
        with(bnd) {
            map.forEach {  (k, v) -> putString(k, v) }
        }
        return bnd
    }
}