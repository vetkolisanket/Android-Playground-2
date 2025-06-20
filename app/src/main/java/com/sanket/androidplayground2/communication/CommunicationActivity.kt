package com.sanket.androidplayground2.communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.databinding.ActivityCommunicationBinding

class CommunicationActivity : AppCompatActivity() {

    private val binding: ActivityCommunicationBinding by lazy { ActivityCommunicationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnSSE.setOnClickListener { openActivity<SSEActivity>() }
    }
}