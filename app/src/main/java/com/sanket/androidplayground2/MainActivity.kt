package com.sanket.androidplayground2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    init()
  }

  private fun init() {
    btnSpeechToText.setOnClickListener {
      startActivity(SpeechToTextActivity.newIntent(this))
    }
  }


}
