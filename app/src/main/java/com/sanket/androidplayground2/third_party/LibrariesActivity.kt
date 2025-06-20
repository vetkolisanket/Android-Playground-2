package com.sanket.androidplayground2.third_party

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.databinding.ActivityLibrariesBinding

class LibrariesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLibrariesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBtns()
    }

    private fun initBtns() {
        binding.btnLottie.setOnClickListener { openActivity<LottieActivity>() }
    }
}