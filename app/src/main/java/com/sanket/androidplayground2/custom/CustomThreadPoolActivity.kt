package com.sanket.androidplayground2.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R

class CustomThreadPoolActivity : AppCompatActivity() {

    private val customThreadPoolService by lazy { CustomThreadPoolService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_thread_pool)

        for (i in 0 until 10) {
            val runnable = Runnable {
                println(i)
            }
            customThreadPoolService.executeWithCustomThreadPool(runnable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        customThreadPoolService.shutdownCustomThreadPool()
    }
}