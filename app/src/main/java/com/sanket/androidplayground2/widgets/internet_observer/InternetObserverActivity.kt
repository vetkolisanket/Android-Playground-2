package com.sanket.androidplayground2.widgets.internet_observer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.databinding.ActivityInternetObserverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InternetObserverActivity : AppCompatActivity() {

    private val viewModel: InternetObserverViewModel by viewModels()
    private val binding by lazy { ActivityInternetObserverBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvNetworkStatus.text = getString(R.string.network_status_s, ConnectivityObserver.Status.Unavailable.name)
        viewModel.internetLd.observe(this) {
            binding.tvNetworkStatus.text = getString(R.string.network_status_s, it.name)
        }
    }
}