package com.sanket.androidplayground2.flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.sanket.androidplayground2.flow.ui.theme.AndroidPlayground2Theme

class FlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPlayground2Theme {
                val viewModel by viewModels<FlowViewModel>()
                val time = viewModel.timerState.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = time.value.toString())
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { viewModel.startTimer() },
                        ) {
                            Text(text = "Start timer")
                        }
                        Button(onClick = { viewModel.startGenerate() }) {
                            Text(text = "Start Export")
                        }
                    }
                    if (viewModel.isLoading) {
                        Dialog(onDismissRequest = { }) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    progress = { viewModel.progressState.toFloat()/100 })
                                Text(
                                    color = Color.White,
                                    text = "Processing user data (${viewModel.progressState} %) ..."
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPlayground2Theme {
        Greeting("Android")
    }
}