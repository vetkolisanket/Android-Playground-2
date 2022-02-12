package com.sanket.androidplayground2.appcomponents.activity

import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.databinding.ActivityAlifecycleBinding

class ALifecycleActivity : AbstractLifecycleActivity() {

    companion object {
        private const val TAG = "ALifecycleActivity"
    }

    private val binding by lazy { ActivityAlifecycleBinding.inflate(layoutInflater) }

    override fun getTag(): String = TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.btnGoToActivityB.setOnClickListener { openActivity<BLifecycleActivity>() }
    }
}