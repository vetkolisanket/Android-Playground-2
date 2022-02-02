package com.sanket.androidplayground2.appcomponents.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sanket.androidplayground2.appcomponents.services.services.MyForegroundService
import com.sanket.androidplayground2.commons.utils.startForegroundService

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            context.startForegroundService<MyForegroundService>()
        }
    }
}