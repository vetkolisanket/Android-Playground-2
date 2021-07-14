package com.sanket.androidplayground2.appcomponents.services.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.appcomponents.services.services.ForegroundService
import com.sanket.androidplayground2.commons.Constants
import kotlinx.android.synthetic.main.activity_foreground_service.*

class ForegroundServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)
        initBtns()
    }

    private fun initBtns() {
        val foregroundService = Intent(this, ForegroundService::class.java)
        foregroundService.putExtra(Constants.TEXT, getString(R.string.description))
        btnStartService.setOnClickListener {
            Toast.makeText(this, getString(R.string.starting_service), Toast.LENGTH_SHORT).show()
            ContextCompat.startForegroundService(this, foregroundService) }
        btnStopService.setOnClickListener {
            Toast.makeText(this, getString(R.string.stopping_service), Toast.LENGTH_SHORT).show()
            stopService(foregroundService)
        }
    }

    /*companion object {
        fun newIntent(context: Context) = Intent(context, ForegroundServiceActivity::class.java)
    }*/
}