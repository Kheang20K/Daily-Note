package com.myapp.dailynote.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp


@Composable
fun AlertDiaLogEnd(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: @Composable () -> Unit,
    dialogText: @Composable () -> Unit,
    icon: ImageVector,
){

    AlertDialog(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        title = {
            dialogTitle()
        },
        text = {
            dialogText()
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 16.sp
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    text = "Dismiss",
                    fontSize = 16.sp
                )
            }
        },
    )
}