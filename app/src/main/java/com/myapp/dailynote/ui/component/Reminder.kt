package com.myapp.dailynote.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Reminder(
    selectedMillis: Long?,
    selectedHour: Int?,
    selectedMinute: Int?,
    onDateTimeSelected: (Long, Int, Int) -> Unit
) {
    var showDate by remember { mutableStateOf(false) }
    var showTime by remember { mutableStateOf(false) }
    // Initialize displayed text from saved values
    val textShowDate = remember(selectedMillis) {
        selectedMillis?.let { formatDate(it) } ?: "Set Reminder"
    }
    val selectionTime = remember(selectedHour, selectedMinute) {
        if (selectedHour != null && selectedMinute != null)
            formatTime(selectedHour, selectedMinute)
        else "Set Time"
    }
    var tempSelectedMillis by remember { mutableStateOf(selectedMillis) }
    var tempHour by remember { mutableIntStateOf(selectedHour ?: 0) }
    var tempMinute by remember { mutableIntStateOf(selectedMinute ?: 0) }

    Column {
        Text("Reminder", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDate = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Notifications, contentDescription = "reminder", modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(6.dp))
            Text("$textShowDate $selectionTime", fontSize = 16.sp)
        }
        Spacer(Modifier.height(12.dp))
        if (showDate) {
            DatePicker(
                onDateSelected = { millis ->
                    millis?.let {
                        tempSelectedMillis = it
                        showDate = false
                        showTime = true
                    }
                },
                onDismiss = { showDate = false }
            )
        }
        if (showTime) {
            TimePicker(
                onTimeSelected = { hour, minute ->
                    tempHour = hour
                    tempMinute = minute
                    showTime = false

                    tempSelectedMillis?.let { millis ->
                        onDateTimeSelected(millis, tempHour, tempMinute)
                    }
                },
                onDismiss = { showTime = false }
            )
        }
    }
}