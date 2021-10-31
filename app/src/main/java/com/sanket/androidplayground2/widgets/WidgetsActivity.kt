package com.sanket.androidplayground2.widgets

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R

class WidgetsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, WidgetsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets)
    }
}