package com.sanket.androidplayground2.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanket.androidplayground2.R
import com.sanket.androidplayground2.commons.utils.openActivity
import com.sanket.androidplayground2.compose.components.AlertDialogHolder
import com.sanket.androidplayground2.compose.components.AnnotatedClickableText
import com.sanket.androidplayground2.compose.components.LetterByLetterAnimatedText
import com.sanket.androidplayground2.compose.components.MultipleStylesInText
import kotlinx.coroutines.launch
import kotlin.random.Random

class ComposeActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember {
                SnackbarHostState()
            }
            val scope = rememberCoroutineScope()
            val openAlertDialog = remember {
                mutableStateOf(false)
            }
            var sliderPosition by remember {
                mutableStateOf(0f..100f)
            }

            ComposeTutorialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(padding)
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MessageCard(Message("Android", "Jetpack Compose"))
                        ImageCard(
                            painter = painterResource(id = R.drawable.dota2),
                            contentDescription = "Dota 2 image",
                            text = "My win streak!",
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                        ColorBox()
                        Button(onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Hi there!",
                                    "Ok",
                                    false,
                                    SnackbarDuration.Indefinite
                                )
                            }
                        }) {
                            Text(text = "Show snackbar")
                        }
                        MultipleStylesInText()
                        AnnotatedClickableText()
                        LetterByLetterAnimatedText()
                        Button(onClick = { openAlertDialog.value = true }) {
                            Text(text = "Open Dialog")
                        }
                        RangeSlider(
                            value = sliderPosition,
                            onValueChange = { range -> sliderPosition = range },
                            valueRange = 0f..100f
                        )
                        Text(text = sliderPosition.toString())
                        Button(onClick = { openActivity<ScaffoldExampleActivity>() }) {
                            Text(text = "Open Large Top App Bar Scaffold Activity")
                        }
                        when {
                            openAlertDialog.value -> {
                                AlertDialogHolder(
                                    onDismissRequest = { openAlertDialog.value = false },
                                    onConfirmation = {
                                        openAlertDialog.value = false
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "Confirmed"
                                            )
                                        }
                                    },
                                    title = "Dummy title",
                                    text = "Lorem Ipsum Dolor Sit Amet",
                                    icon = Icons.Default.Info
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
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2)
    ) {

    }
}

@Composable
fun ColorBox() {
    var color by remember {
        mutableStateOf(
            Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        )
    }
    var sizeState by remember {
        mutableStateOf(Random.nextInt(50, 300).dp)
    }
    val size by animateDpAsState(targetValue = sizeState, label = "box size animation")
    Box(
        modifier = Modifier
            .size(size)
            .background(color)
            .clickable {
                color = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
                sizeState = Random.nextInt(50, 300).dp
            }
    )
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_mic),
            contentDescription = "Picture",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = message.author, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = message.body, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(Message("Sanket", "Blah blah blah"))
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(modifier = Modifier.height(180.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Text(
                text = text,
                style = TextStyle(
                    color = Color.White,
                ),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewImageCard() {
    ImageCard(
        painter = painterResource(id = R.drawable.dota2),
        contentDescription = "Dota 2 image",
        text = "My win streak!"
    )
}