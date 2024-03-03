package com.sanket.androidplayground2.custom

import kotlinx.coroutines.Runnable

interface ICustomThreadPoolService {
    fun executeWithCustomThreadPool(task: Runnable)
    fun shutdownCustomThreadPool()
}