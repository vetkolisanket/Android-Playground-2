package com.sanket.androidplayground2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_speech_to_text.*

class SpeechToTextActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SpeechToTextActivity::class.java)
    }

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
