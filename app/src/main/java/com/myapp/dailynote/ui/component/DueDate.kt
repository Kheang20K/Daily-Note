package com.myapp.dailynote.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
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
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.dailynote.R
import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.logging.SimpleFormatter

@Composable
fun DueDate(

){
    var showDate by remember { mutableStateOf(false) }
    var showTime by remember { mutableStateOf(false) }
    var textShowDate by remember { mutableStateOf<String?>(null)}
    var selectionTime by remember { mutableStateOf<String?>(null) }

//    var selectionTime by remember { mutableStateOf<Pair<Int,Int>?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Due Date",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(12.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showDate = true
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "add",
                modifier = Modifier
                    .size(28.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = textShowDate ?:"Set Due Date",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
        Spacer(Modifier.height(12.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showTime = true
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                painter = painterResource(id = R.drawable.clock),
                contentDescription = "add time",
                modifier = Modifier
                    .size(28.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = selectionTime ?:"Set Time",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
        if (showDate){
            DatePicker(
                onDateSelected = { millis ->
                    millis?.let {
                        textShowDate = formatDate(millis)
                    }

                },
                onDismiss = {
                    showDate = false
                }
            )
        }else if (showTime){
            TimePicker(
                onTimeSelected ={hour,minute ->
                    selectionTime = formatTime(hour,minute)
                    showTime = false

                },
                onDismiss = {
                    showTime = false
                }
            )

        }
    }
}




@Preview
@Composable
fun pr(){
    DueDate()
}