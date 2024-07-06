package com.sanket.androidplayground2.flow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.androidplayground2.flow.data.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowViewModel(
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

    private val _timerState = MutableStateFlow(10)
    val timerState = _timerState.asStateFlow()
    private var job: Job? = null

    var progressState by mutableStateOf(0)
        private set

    var isLoading by mutableStateOf(false)
        private set

    private val userData = mutableListOf<Float>()

    init {
        for (i in 0..100000) {
            userData.add(i * 2f)
        }
    }

    fun startGenerate() {
        isLoading = true
        viewModelScope.launch {
            userRepository.generateUserData(userData).collect {
                progressState = it.progressPercentage
            }
            isLoading = false
            progressState = 0
        }
    }

    fun startTimer() {
        if (job?.isActive == true) return
        timerStartTime = 10
        job = viewModelScope.launch {
            countDownTimer.collect {
                _timerState.emit(it)
            }
        }
    }

    var timerStartTime = 10
    val countDownTimer = flow {
        var curTime = timerStartTime
        while (curTime >= 0) {
            emit(curTime)
            delay(1000)
            curTime--
        }
    }

}