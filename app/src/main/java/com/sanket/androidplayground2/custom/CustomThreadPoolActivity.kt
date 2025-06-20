package com.sanket.androidplayground2.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.databinding.ActivityCustomThreadPoolBinding

class CustomThreadPoolActivity : AppCompatActivity() {

    private val customThreadPoolService by lazy { CustomThreadPoolService() }
    private val binding by lazy { ActivityCustomThreadPoolBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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