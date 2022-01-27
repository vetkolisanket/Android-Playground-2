package com.sanket.androidplayground2.appcomponents.services.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.appcomponents.services.services.TimerService
import com.sanket.androidplayground2.databinding.ActivityStopWatchTimerBinding
import kotlin.math.roundToInt

class StopWatchTimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStopWatchTimerBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStartStop.setOnClickListener { startStopTimer() }
            btnReset.setOnClickListener { resetTimer() }
        }

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.tvTimer.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if (timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        binding.btnStartStop.apply {
            text = getString(R.string.stop)
            icon = ContextCompat.getDrawable(this@StopWatchTimerActivity, R.drawable.ic_pause)
        }
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.btnStartStop.apply {
            text = getString(R.string.start)
            icon = ContextCompat.getDrawable(this@StopWatchTimerActivity, R.drawable.ic_play)
        }
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.tvTimer.text = getTimeStringFromDouble(time)
        }

    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, seconds: Int) =
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
}