package com.sanket.androidplayground2.appcomponents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R

class AppComponentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_components)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AppComponentsActivity::class.java)
    }
}