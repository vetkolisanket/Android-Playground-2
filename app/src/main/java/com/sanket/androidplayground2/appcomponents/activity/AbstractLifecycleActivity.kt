package com.sanket.androidplayground2.appcomponents.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

abstract class AbstractLifecycleActivity : AppCompatActivity() {

    abstract fun getTag(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("onCreate")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        log("onPostCreate")
    }

    override fun onStart() {
        super.onStart()
        log(this::onStart.name)
    }

    override fun onResume() {
        super.onResume()
        log(this::onResume.name)
    }

    override fun onPostResume() {
        super.onPostResume()
        log("onPostResume")
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        log("onResumeFragments")
    }

    override fun onPause() {
        super.onPause()
        log(this::onPause.name)
    }

    override fun onStop() {
        super.onStop()
        log(this::onStop.name)
    }

    override fun onRestart() {
        super.onRestart()
        log(this::onRestart.name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        log("onRestoreInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        log(this::onDestroy.name)
    }

    private fun log(functionName: String) {
        val string = "$functionName called"
        Log.d(getTag(), string)
    }
}