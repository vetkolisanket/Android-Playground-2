package com.sanket.androidplayground2.third_party

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.openActivity
import kotlinx.android.synthetic.main.activity_libraries.*

class LibrariesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libraries)

        initBtns()
    }

    private fun initBtns() {
        btnLottie.setOnClickListener { openActivity<LottieActivity>() }
    }
}