package com.sanket.androidplayground2.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.Status
import com.sanket.androidplayground2.commons.utils.hide
import com.sanket.androidplayground2.commons.utils.show
import com.sanket.androidplayground2.databinding.ActivityLongRunningTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LongRunningTaskActivity : AppCompatActivity() {

    private val viewModel: CoroutinesViewModel by viewModels()
    private val binding by lazy { ActivityLongRunningTaskBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObserver()
        viewModel.doLongRunningTask()
    }

    private fun setupObserver() {
        viewModel.getStatus().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.show()
                    Toast.makeText(this, getString(R.string.long_running_task_started), Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    binding.progressBar.hide()
                    Toast.makeText(this, it.data, Toast.LENGTH_SHORT).show()
                }
                else -> binding.progressBar.hide()
            }
        }
    }
}