package com.sanket.androidplayground2.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_hilt.*

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt)
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        userViewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.isGone = true
                    it.data?.let { users -> renderList(users) }
                    recyclerView.isVisible = true
                }
                Status.LOADING -> {
                    progressBar.isVisible = true
                    recyclerView.isGone = true
                }
                Status.ERROR -> {
                    progressBar.isGone = true
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
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HiltActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@HiltActivity,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = this@HiltActivity.adapter
        }

    }
}