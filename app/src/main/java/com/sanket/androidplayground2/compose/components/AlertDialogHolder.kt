package com.sanket.androidplayground2.compose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AlertDialogHolder(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    text: String,
    icon: ImageVector
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(text = "Confirm")
            }
        },
        icon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = "Dismiss")
            }
        }
    )

}