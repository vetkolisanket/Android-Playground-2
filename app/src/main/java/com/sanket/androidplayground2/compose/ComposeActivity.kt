package com.sanket.androidplayground2.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanket.androidplayground2.R
import kotlin.random.Random

class ComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White)
                ) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                    ImageCard(
                        painter = painterResource(id = R.drawable.dota2),
                        contentDescription = "Dota 2 image",
                        text = "My win streak!",
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    ColorBox()
                }
            }
        }
    }
}

@Composable
fun ColorBox() {
    val color = remember {
        mutableStateOf(
            Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        )
    }

    Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
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