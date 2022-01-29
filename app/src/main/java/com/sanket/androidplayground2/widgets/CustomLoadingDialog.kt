package com.sanket.androidplayground2.widgets

import android.app.Activity
import android.app.AlertDialog
import com.sanket.androidplayground2.R

class CustomLoadingDialog(val activity: Activity) {

    private var dialog: AlertDialog? = null

    fun showLoadingDialog() {
        val dialogBuilder = AlertDialog.Builder(activity)

        dialogBuilder.setView(activity.layoutInflater.inflate(R.layout.dialog_loading, null))
        dialogBuilder.setCancelable(true)

        dialog = dialogBuilder.create()
        dialog?.show()
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

}