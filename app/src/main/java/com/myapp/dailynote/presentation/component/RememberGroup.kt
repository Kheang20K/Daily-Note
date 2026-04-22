package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myapp.dailynote.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RememberGroup(){
//    var showDatePicker by remember { mutableStateOf(false) }
//    val datePickerState = rememberDatePickerState()
//    var onSelectedDate by remember { mutableStateOf<Long?> (null) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .shadow(
//                elevation = 1.dp,
//                shape = RoundedCornerShape(12.dp),
//            )
//            .background(Color.White)
//    ){
//        Column (
//            modifier = Modifier
//                .padding(12.dp)
//        ){
//            Row (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable(
//                        onClick = {
//                            showDatePicker = true
//                        }
//                    )
//            ){
//                GroupRowUi(
//                    iconStart = painterResource(id = R.drawable.ic_date_range),
//                    title = "DueDate",
//                    date = convertMillisToDate(onSelectedDate),
//                    iconEnd = painterResource(id = R.drawable.ic_arrow)
//                )
//            }
//            Spacer(Modifier.height(16.dp))
//            GroupRowUi(
//                iconStart = painterResource(id = R.drawable.ic_sharp_timer),
//                title = "Time",
//                date = "dasd",
//                iconEnd = painterResource(id = R.drawable.ic_arrow)
//            )
//            Spacer(Modifier.height(16.dp))
//            GroupRowUi(
//                iconStart = painterResource(id = R.drawable.ic_date_range),
//                title = "Remember at",
//                date = "dsa",
//                iconEnd = painterResource(id = R.drawable.ic_arrow)
//            )
//            Spacer(Modifier.height(16.dp))
//            GroupRowUi(
//                iconStart = painterResource(id = R.drawable.ic_date_range),
//                title = "Repeat",
//                date = "asd",
//                iconEnd = painterResource(id = R.drawable.ic_arrow)
//            )
//        }
//        if (showDatePicker){
//            DatePickerModal(
//                onDateSelected = {
//                    onSelectedDate = it
//                },
//                onDismiss = {
//                    showDatePicker = false
//                }
//            )
//        }
//    }
//}
//
//fun convertMillisToDate(millis : Long?): String{
//    if (millis == null) return "No date"
//    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//    return millis?.let {
//        formatter.format(Date(it))
//    } ?: "Select date"
//}


