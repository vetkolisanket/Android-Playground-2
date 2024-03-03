package com.sanket.androidplayground2.custom

import kotlinx.coroutines.Runnable

class CustomThreadPoolService: ICustomThreadPoolService {

    private val threadPool: Array<Thread> = Array(THREAD_POOL_SIZE) {
        createWorkerThread()
    }

    private fun createWorkerThread(): Thread {
        val thread = Thread {
            while (!Thread.currentThread().isInterrupted) {
                //Perform assigned task if available
            }
        }.apply { start() }
        println(thread)
        return thread
    }

    override fun executeWithCustomThreadPool(task: Runnable) {
        for (i in 0 until THREAD_POOL_SIZE) {
            if (!threadPool[i].isAlive) {
                Thread(task).apply {
                    threadPool[i] = this
                    start()
                }
                return
            }
        }
        println("No threads available")
    }

    override fun shutdownCustomThreadPool() {
        for (i in 0 until THREAD_POOL_SIZE) {
            threadPool[i].interrupt()
        }
    }

    companion object {
        private const val THREAD_POOL_SIZE = 3
    }
}