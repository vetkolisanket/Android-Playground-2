package com.sanket.androidplayground2.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.Status
import com.sanket.androidplayground2.data.model.User
import com.sanket.androidplayground2.databinding.ActivityHiltBinding
import com.sanket.androidplayground2.di.module.ApiKey
import com.sanket.androidplayground2.di.module.LibraryKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    companion object {
        val TAG = HiltActivity::class.java.simpleName
    }

    @ApiKey
    @Inject
    lateinit var apiKey: String

    @LibraryKey
    @Inject
    lateinit var libraryKey: String

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter
    private val binding by lazy { ActivityHiltBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        setupObserver()
        Log.d(TAG, "onCreate: $apiKey")
        Log.d(TAG, "onCreate: $libraryKey")
    }

    private fun setupObserver() {
        userViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.isGone = true
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.isVisible = true
                }

                Status.LOADING -> {
                    binding.apply {
                        progressBar.isVisible = true
                        recyclerView.isGone = true
                    }
                }

                Status.ERROR -> {
                    binding.progressBar.isGone = true
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.apply {
            addData(users)
            notifyDataSetChanged()
        }
    }

    private fun setupUI() {
        adapter = UserAdapter(arrayListOf())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HiltActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@HiltActivity,
                    (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = this@HiltActivity.adapter
        }

    }
}