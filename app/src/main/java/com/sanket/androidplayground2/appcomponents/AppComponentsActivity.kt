package com.sanket.androidplayground2.appcomponents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.appcomponents.activity.ExamplesActivity
import com.sanket.androidplayground2.appcomponents.services.activities.ServicesExampleActivity
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.databinding.ActivityAppComponentsBinding

class AppComponentsActivity : AppCompatActivity() {

    val binding by lazy { ActivityAppComponentsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initBtns()
    }

    private fun initBtns() {
        binding.btnService.setOnClickListener { openActivity<ServicesExampleActivity>() }
        binding.btnActivity.setOnClickListener { openActivity<ExamplesActivity>() }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AppComponentsActivity::class.java)
    }
}