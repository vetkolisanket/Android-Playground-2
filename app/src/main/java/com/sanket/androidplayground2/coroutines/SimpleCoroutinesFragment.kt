package com.sanket.androidplayground2.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sanket.androidplayground2.databinding.FragmentSimpleCoroutinesBinding
import kotlinx.android.synthetic.main.fragment_simple_coroutines.*
import kotlinx.android.synthetic.main.fragment_simple_coroutines.view.*
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.random.Random

class SimpleCoroutinesFragment private constructor() : Fragment() {

    private lateinit var binding: FragmentSimpleCoroutinesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleCoroutinesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            setupBtnWork1()
            setupBtnWork2()
            setupBtnWorkSerially()
            setupBtnWorkParallelly()
            setupBtnWithTimeoutOrNull()
            setupBtnSuspendCancellableCoroutine()
        }
    }

    private fun setupBtnSuspendCancellableCoroutine() {
        btnSuspendCancellableCoroutine.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val workString = withTimeoutOrNull(2000) {
                    val work1 = doWork1(Random.nextLong(500, 1500))
                    val work2 = doWork2(Random.nextLong(500, 1500))
                    val work = suspendCancellableCoroutine<String> {
                        it.resume("$work1 $work2")
                        it.invokeOnCancellation {
                            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
                        }
                    }
                    work
                }
                workString?.let { tvResult.text = it }
            }
        }
    }

    private fun setupBtnWithTimeoutOrNull() {
        btnWithTimeoutOrNull.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val time = System.currentTimeMillis()
                val work = withTimeoutOrNull(2000) {
                    val work1 = doWork1(Random.nextLong(500, 1500))
                    val work2 = doWork2(Random.nextLong(500, 1500))
                    "$work1 $work2"
                }
                withContext(Dispatchers.Main) {
                    work?.let {
                        tvResult.text = it
                        Toast.makeText(
                            context,
                            "Time taken with timeout ${System.currentTimeMillis() - time}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } ?: kotlin.run {
                        Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupBtnWorkParallelly() {
        btnWorkParallelly.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val time = System.currentTimeMillis()
                val work1Async = async { doWork1() }
                val work2Async = async { doWork2() }
                val text = StringBuilder()
                text.append("${work1Async.await()} ")
                text.append("${work2Async.await()} ")
                withContext(Dispatchers.Main) {
                    tvResult.text = text.trim().toString()
                    Toast.makeText(
                        context,
                        "Time taken parallelly ${System.currentTimeMillis() - time}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun View.setupBtnWorkSerially() {
        btnWorkSerially.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val time = System.currentTimeMillis()
                val work1 = doWork1()
                val work2 = doWork2()
                withContext(Dispatchers.Main) {
                    tvResult.text = "$work1 $work2"
                    Toast.makeText(
                        context,
                        "Time taken serially ${System.currentTimeMillis() - time}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun View.setupBtnWork2() {
        btnWork2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val time = System.currentTimeMillis()
                val work2 = doWork2()
                withContext(Dispatchers.Main) {
                    tvResult.text = work2
                    Toast.makeText(
                        context,
                        "Time taken for work two ${System.currentTimeMillis() - time}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun View.setupBtnWork1() {
        btnWork1.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val time = System.currentTimeMillis()
                val work1 = doWork1()
                withContext(Dispatchers.Main) {
                    tvResult.text = work1
                    Toast.makeText(
                        context,
                        "Time taken for work one ${System.currentTimeMillis() - time}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private suspend fun doWork1(time: Long = 1000): String {
        delay(time)
        return "Work 1"
    }

    private suspend fun doWork2(time: Long = 1000): String {
        delay(time)
        return "Work 2"
    }

    companion object {
        fun newInstance(): SimpleCoroutinesFragment {
            return SimpleCoroutinesFragment()
        }
    }

}