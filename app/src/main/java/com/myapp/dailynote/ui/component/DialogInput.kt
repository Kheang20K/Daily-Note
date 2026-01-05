package com.myapp.dailynote.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DialogInput(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: @Composable () -> Unit,
    textField: @Composable (() -> Unit),
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            onDismissRequest()
        },
        title = {
            title()
        },
        text = {
            textField()
        },
        dismissButton = {
            Text(
                text = "Cancel",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onDismissRequest()
                        }
                    )
            )
        },
        confirmButton = {
            Text(
                text = "Ok",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onConfirmation()
                        }
                    )
            )
        }
    )
}