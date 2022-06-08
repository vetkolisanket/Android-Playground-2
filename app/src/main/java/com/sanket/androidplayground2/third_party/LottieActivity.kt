package com.sanket.androidplayground2.third_party

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.R
import kotlinx.android.synthetic.main.activity_lottie.*

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)

        initBtns()
    }

    private fun initBtns() {
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