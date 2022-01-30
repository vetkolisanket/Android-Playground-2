package com.sanket.androidplayground2.appcomponents.services.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.appcomponents.services.services.MyBackgroundService
import com.sanket.androidplayground2.commons.utils.openActivity
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
            btnForegroundService.setOnClickListener { openActivity<ForegroundServiceActivity>() }
            btnStopWatchWithService.setOnClickListener { openActivity<StopWatchTimerActivity>() }
            btnBackgroundService.setOnClickListener { startService<MyBackgroundService>() }
        }

    }
}