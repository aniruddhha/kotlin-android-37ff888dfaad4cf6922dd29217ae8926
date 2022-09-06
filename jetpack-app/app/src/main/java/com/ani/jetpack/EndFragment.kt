package com.ani.jetpack

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EndFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val bld  = AlertDialog.Builder(requireContext())
        bld.setPositiveButton("Yes", ) { dl, id -> }
        bld.setNegativeButton("Yes", ) { dl, id -> }

        return super.onCreateDialog(savedInstanceState)
    }
}