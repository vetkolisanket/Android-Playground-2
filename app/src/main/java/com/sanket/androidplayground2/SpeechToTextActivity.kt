package com.sanket.androidplayground2

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.databinding.ActivitySpeechToTextBinding
import java.util.*

class SpeechToTextActivity : AppCompatActivity() {

    private val REQUEST_CODE_SPEECH_INPUT: Int = 1001

    companion object {
        fun newIntent(context: Context) = Intent(context, SpeechToTextActivity::class.java)
    }

    private val binding by lazy { ActivitySpeechToTextBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.ivMic.setOnClickListener {
            promptSpeechInput()
        }
    }

    private fun promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt))
        }
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(this, R.string.speech_not_supported, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result =
                        data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
