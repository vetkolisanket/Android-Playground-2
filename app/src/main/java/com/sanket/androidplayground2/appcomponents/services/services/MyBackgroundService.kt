package com.sanket.androidplayground2.appcomponents.services.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class MyBackgroundService: Service() {

    private val handler by lazy { Handler(Looper.myLooper()!!) }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        logViaThread()
        logViaHandler()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun logViaHandler() {
        handler.apply {
            post { Log.e("MyBackgroundService", "Service Running") }
            postDelayed({ logViaHandler() }, 2000)
        }
    }

    private fun logViaThread() {
        Thread {
            while (true) {
                Log.e("MyBackgroundService", "Service Running")
                Thread.sleep(2000)
            }
        }.start()
    }
}