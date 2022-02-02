package com.sanket.androidplayground2.appcomponents.services.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.sanket.androidplayground2.R

class MyForegroundService : Service() {

    companion object {
        const val CHANNEL_ID = "FOREGROUND SERVICE CHANNEL ID"
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logViaThread()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_LOW)

            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
            val notificationBuilder = Notification.Builder(this, CHANNEL_ID)
                .setContentText("My foreground service is running")
                .setContentTitle("Service enabled")
                .setSmallIcon(R.drawable.ic_launcher_foreground)

            startForeground(1001, notificationBuilder.build())
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun logViaThread() {
        Thread {
            while (true) {
                Log.e("MyForegroundService", "Service Running")
                Thread.sleep(2000)
            }
        }.start()
    }
}