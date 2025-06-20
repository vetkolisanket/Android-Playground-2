package com.sanket.androidplayground2.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sanket.androidplayground2.compose.components.LargeTopAppBarScaffold
import com.sanket.androidplayground2.compose.ui.theme.AndroidPlayground2Theme

class ScaffoldExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AndroidPlayground2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LargeTopAppBarScaffold(
                        onBackClick = ::finish
                    )
                }
            }
        }
    }
}

