package com.sanket.androidplayground2.appcomponents.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.sanket.androidplayground2.R

abstract class AbstractLifecycleActivity : AppCompatActivity() {

    abstract fun getTag(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logAndToast("onCreate")
    }

    private fun logAndToast(functionName: String) {
        val string = "$functionName called"
        Log.d(getTag(), string)
//        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        logAndToast(this::onStart.name)
    }

    override fun onResume() {
        super.onResume()
        logAndToast(this::onResume.name)
    }

    override fun onPause() {
        super.onPause()
        logAndToast(this::onPause.name)
    }

    override fun onStop() {
        super.onStop()
        logAndToast(this::onStop.name)
    }

    override fun onRestart() {
        super.onRestart()
        logAndToast(this::onRestart.name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logAndToast("onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logAndToast("onRestoreInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        logAndToast(this::onDestroy.name)
    }
}