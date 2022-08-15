package com.sanket.androidplayground2.widgets.internet_observer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sanket.androidplayground2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_internet_observer.*

@AndroidEntryPoint
class InternetObserverActivity : AppCompatActivity() {

    private val viewModel: InternetObserverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet_observer)
        tvNetworkStatus.text = getString(R.string.network_status_s, ConnectivityObserver.Status.Unavailable.name)
        viewModel.internetLd.observe(this) {
            tvNetworkStatus.text = getString(R.string.network_status_s, it.name)
        }
    }
}