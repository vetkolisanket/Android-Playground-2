package com.sanket.androidplayground2.appcomponents.services.activities

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.appcomponents.services.services.MyBackgroundService
import com.sanket.androidplayground2.appcomponents.services.services.MyForegroundService
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.commons.utils.startForegroundService
import com.sanket.androidplayground2.commons.utils.startService
import com.sanket.androidplayground2.databinding.ActivityServicesExampleBinding

class ServicesExampleActivity : AppCompatActivity() {

    private val binding by lazy { ActivityServicesExampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBtns()
    }

    private fun initBtns() {
        binding.apply {
            btnForegroundService2.setOnClickListener { openActivity<ForegroundServiceActivity>() }
            btnStopWatchWithService.setOnClickListener { openActivity<StopWatchTimerActivity>() }
            btnBackgroundService.setOnClickListener { startService<MyBackgroundService>() }
            btnForegroundService.setOnClickListener { startMyForegroundService() }
        }
    }

    private fun startMyForegroundService() {
        if (isServiceRunning(MyForegroundService::class.java.simpleName).not()) {
            startForegroundService<MyForegroundService>()
        }
    }

    private fun isServiceRunning(serviceName: String): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningService in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (runningService.service.className == serviceName) return true
        }
        return false
    }
}