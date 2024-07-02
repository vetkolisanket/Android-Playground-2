package com.sanket.androidplayground2.compose.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AnnotatedClickableText() {
    val annotatedText = buildAnnotatedString {
        append("Go to ")

        pushStringAnnotation(tag = "URL", annotation = "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold)) {
            append("Android Developers")
        }
        pop()

        append(" and check the ")

        pushStringAnnotation(tag = "URL", annotation = "https://developer.android.com/jetpack/compose")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append("Compose guidelines.")
        }
        pop()
    }

    ClickableText(text = annotatedText, onClick = { offset ->
        annotatedText.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()?.let { annotation ->
            Log.d("Clicked URL", annotation.item)
        }
    })
}