package com.sanket.androidplayground2.third_party

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.databinding.ActivityLottieBinding

class LottieActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLottieBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBtns()
    }

    private fun initBtns() {
        binding.apply {
            btnBullseye.setOnClickListener {
                lav.setAnimation(R.raw.bullseye)
                lav.playAnimation()
            }
            btnFullScreen.setOnClickListener {
                lav.setAnimation(R.raw.full_screen)
                lav.playAnimation()
            }
            btnHamburgerArrow.setOnClickListener {
                lav.setAnimation(R.raw.hamburger_arrow)
                lav.playAnimation()
            }
        }
    }
}