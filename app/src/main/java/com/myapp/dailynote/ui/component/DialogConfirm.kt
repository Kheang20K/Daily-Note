package com.myapp.dailynote.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogConfirm(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    destructive: Boolean = true
) {
    AlertDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            title()
        },
        text = {
            text()
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    modifier = Modifier.weight(1f),
                    onClick = onDismissRequest
                ) {
                    Text("Cancel")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (destructive) MaterialTheme.colorScheme.error
                        else MaterialTheme.colorScheme.primary
                    ),
                    onClick = onConfirmation
                ) {
                    Text(
                        text = if (destructive) "Delete" else "OK",
                        color = MaterialTheme.colorScheme.onError // readable on red
                    )
                }
            }
        }
    )
}