package com.ani.dlgnoti

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

inline fun DialogFragment.toast(message: () -> String) {
    Toast.makeText(this.context, message(), Toast.LENGTH_LONG).show()
}

class AppDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return when(tag) {
            "otp" -> showOtpDialog()
            "upload" -> imageUploadProgressDialog()
            else -> showOtpDialog()
        }
    }

    private fun showOtpDialog() = AlertDialog.Builder(requireContext()).apply {
            setPositiveButton("Yes") { di, id -> toast { "Yes Clicked" } }
            setNegativeButton("No") { di, id -> toast { "No Clicked" } }
            setMessage("Message")
            setTitle("Title")
    }.create()

    private fun imageUploadProgressDialog() = AlertDialog.Builder(requireContext()).apply {
        setTitle("Uploading")
        setView(layoutInflater.inflate(R.layout.progress_dialog, null, false))
    }.create()
}