package com.sanket.androidplayground2.appcomponents.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.databinding.ActivityBlifecycleBinding

class BLifecycleActivity : AbstractLifecycleActivity() {

    companion object {
        private const val TAG = "BLifecycleActivity"
    }

    private val binding by lazy { ActivityBlifecycleBinding.inflate(layoutInflater) }

    override fun getTag() = TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}