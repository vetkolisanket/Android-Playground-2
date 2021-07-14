package com.sanket.androidplayground2.appcomponents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.appcomponents.services.activities.ServicesExampleActivity
import com.sanket.androidplayground2.commons.openActivity
import kotlinx.android.synthetic.main.activity_app_components.*

class AppComponentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_components)
        initBtns()
    }

    private fun initBtns() {
        btnService.setOnClickListener { openActivity<ServicesExampleActivity>() }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AppComponentsActivity::class.java)
    }
}