package com.sanket.androidplayground2.appcomponents.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.databinding.ActivityExamplesBinding

class ExamplesActivity : AppCompatActivity() {

    val binding by lazy { ActivityExamplesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.btnLifecycle.setOnClickListener { openActivity<ALifecycleActivity>() }
    }
}