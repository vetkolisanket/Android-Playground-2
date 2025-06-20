package com.sanket.androidplayground2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.androidplayground2.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
