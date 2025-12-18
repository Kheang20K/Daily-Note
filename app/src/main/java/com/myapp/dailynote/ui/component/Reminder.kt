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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.dailynote.R
import com.myapp.dailynote.data.viewmodel.DateTimeViewModel

@Composable
fun Reminder(
    viewModel: DateTimeViewModel = hiltViewModel()
){
    var showDate by remember { mutableStateOf(false) }
    var showTime by remember { mutableStateOf(false) }

    var textShowDate by remember { mutableStateOf<String?>(null) }
    var selectionTime by remember { mutableStateOf<String?>(null) }

    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Reminder",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showDate = true
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "notifications",
                modifier = Modifier
                    .size(28.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = if (textShowDate != null && selectionTime != null) {
                    "$textShowDate $selectionTime"
                } else {
                    "Set Reminder"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
        Spacer(Modifier.height(12.dp))

        if (showDate) {
            DatePicker(
                onDateSelected = { millis ->
                    millis?.let {
                        textShowDate = formatDate(millis)
                        showDate = false
                        showTime = true
                    }

                },
                onDismiss = {
                    showDate = false
                }
            )
        }
        if (showTime) {
            TimePicker(
                onTimeSelected = { hour, minute ->
                    selectionTime = formatTime(hour, minute)
                    selectedHour = hour
                    selectedMinute = minute
                    showTime = false

                    selectedDateMillis?.let { DateMillis ->
                        viewModel.saveReminder(
                            dateMillis = DateMillis,
                            hour = selectedHour,
                            minute = selectedMinute
                        )
                    }

                },
                onDismiss = {
                    showTime = false

                }
            )
        }
    }
}