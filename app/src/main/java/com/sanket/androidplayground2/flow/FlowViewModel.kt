package com.sanket.androidplayground2.flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.androidplayground2.commons.utils.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowViewModel : ViewModel() {
    fun startTimer() {
        viewModelScope.launch {
            countDownTimer.collect {
                Log.d(TAG, "startTimer: $it")
            }
        }
    }

    var timerStartTime = 10
    val countDownTimer = flow<Int> {
        var curTime = timerStartTime
        while (curTime >= 0) {
            emit(curTime)
            delay(1000)
            curTime--
        }
    }

}