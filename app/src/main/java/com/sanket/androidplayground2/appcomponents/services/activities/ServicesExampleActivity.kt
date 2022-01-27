package com.sanket.androidplayground2.appcomponents.services.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.openActivity
import kotlinx.android.synthetic.main.activity_services_example.*

class ServicesExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services_example)
        initBtns()
    }

    private fun initBtns() {
        btnForegroundService.setOnClickListener { openActivity<ForegroundServiceActivity>() }
        btnStopWatchWithService.setOnClickListener { openActivity<StopWatchTimerActivity>() }
    }
}