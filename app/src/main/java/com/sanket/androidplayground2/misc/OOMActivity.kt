package com.sanket.androidplayground2.misc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.sanket.androidplayground2.compose.ui.theme.AndroidPlayground2Theme
import com.sanket.androidplayground2.misc.OOMActivity.Companion.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OOMActivity : ComponentActivity() {

    companion object {
        const val TAG = "logCurrentMemoryProfile"
    }

    private val idList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        logCurrentMemoryProfile("onCreate start")

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPlayground2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    OOMCompose(
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        ::execute
                    )
                }
            }
        }

        logCurrentMemoryProfile("onCreate after setContent")

        lifecycleScope.launch {
            delay(10_000)
            logCurrentMemoryProfile("onCreate after delay")
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.d(TAG, "onTrimMemory: ")
    }

    private fun execute() {
        for (i in 1..1_000_000) {
            idList.add(i)
        }
        logCurrentMemoryProfile("execute current id list size: ${idList.size}")
    }
}

@Composable
fun OOMCompose(modifier: Modifier = Modifier, block: () -> Unit = {}) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                block()
            },
            modifier = Modifier

        ) {
            Text("Add items")
        }
        Button(
            onClick = {

            },
            modifier = Modifier

        ) {
            Text("Create threads")
        }
    }
}

fun logCurrentMemoryProfile(src: String) {
    val maxMemoryInMB = Runtime.getRuntime().maxMemory() / 1024 / 1024
    val totalMemoryInMB = Runtime.getRuntime().totalMemory() / 1024 / 1024
    val freeMemoryInMB = Runtime.getRuntime().freeMemory() / 1024 / 1024
    val usedMemoryInMB = totalMemoryInMB - freeMemoryInMB

    Log.d(TAG, "*************************")

    Log.d(TAG, "Start of the source: $src")

    Log.d(TAG, "maxMemoryInMB: $maxMemoryInMB")
    Log.d(TAG, "totalMemoryInMB: $totalMemoryInMB")
    Log.d(TAG, "freeMemoryInMB: $freeMemoryInMB")
    Log.d(TAG, "usedMemoryInMB: $usedMemoryInMB")

    Log.d(TAG, "End of the source: $src")

    Log.d(TAG, "*************************")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    AndroidPlayground2Theme {
        OOMCompose()
    }
}