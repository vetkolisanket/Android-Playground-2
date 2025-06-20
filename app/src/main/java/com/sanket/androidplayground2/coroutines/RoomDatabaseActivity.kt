package com.sanket.androidplayground2.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanket.androidplayground2.commons.utils.Status
import com.sanket.androidplayground2.data.model.User
import com.sanket.androidplayground2.databinding.ActivityRoomDatabaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomDatabaseActivity : AppCompatActivity() {

    private val adapter by lazy { ApiUserAdapter(mutableListOf()) }
    private val viewModel: CoroutinesViewModel by viewModels()
    private val binding by lazy { ActivityRoomDatabaseBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUI()
        setupObserver()
        viewModel.fetchUsersFromDB()
    }

    private fun setupUI() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@RoomDatabaseActivity)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }

                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}