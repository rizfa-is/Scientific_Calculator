package com.istekno.mycalculator.ui

import android.app.AlertDialog
import android.content.Context

class Dialog(private val context: Context?) {

    fun dialog(message: String, listAction: () -> Unit) {
        val dialog = AlertDialog.Builder(context).apply {
            setTitle("Notice")
            setMessage(message)
            setCancelable(false)
            setPositiveButton("Yes") { _, _ ->
                listAction()
            }
            setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }
        dialog.show()
    }
}