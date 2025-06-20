package com.sanket.androidplayground2.widgets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.databinding.ActivityWidgetsBinding

class WidgetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetsBinding
    private val dialog by lazy { CustomLoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWidgetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoadingDialog.setOnClickListener {
            dialog.showLoadingDialog()
        }
    }
}