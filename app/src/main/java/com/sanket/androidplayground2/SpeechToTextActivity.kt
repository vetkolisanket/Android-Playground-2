package com.sanket.androidplayground2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_speech_to_text.*

class SpeechToTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_to_text)
        init()
    }

    private fun init() {
        ivMic.setOnClickListener {

        }
    }

}
