package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.screen.detail.AllDetailViewModel
import com.myapp.dailynote.presentation.screen.home.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    showBottomSheet: Boolean,
    text : String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onClickable: (String,Long?) -> Unit,
    onSelectedDate: (Long) -> Unit,
    onSelectedTimer: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    allDetailViewModel: AllDetailViewModel = hiltViewModel()

){
    val selectedId by viewModel.selectedFolderId.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val folder  by allDetailViewModel.folders.collectAsState()
    val selectedTime by allDetailViewModel.selectedTimerDate.collectAsState()

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }

    var showTimerPicker by remember { mutableStateOf(false) }
//    create timein Millis
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false
    )


    if (showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            sheetState = sheetState
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(start = 16.dp, end = 16.dp, top = 6.dp)
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            onClickable(text,selectedDate)
                        },
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF2BBE66),
                        modifier = Modifier
                            .size(32.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                TextFieldCustom(
                    value = text,
                    onChangeValue = onTextChange,
                    maxLength = 50,
                    labelStr = "What is to be done?"
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Select Your Category!",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                ){
                    Box (
                        modifier = Modifier
                            .weight(0.8f)
                            .height(36.dp)
                    ){
                        LazyRow (
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ){
                            // ✅ Folders
                            items(folder) { item ->
                                ChipUi(
                                    title = item.folderName,
                                    isSelected = selectedId == item.folderId,
                                    onClick = {
                                        viewModel.selectFolder(item.folderId)
                                    },
                                    modifier = Modifier
                                        .height(50.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                showDatePicker = true
                            }
                        )
                ){
                    GroupRowUi(
                        iconStart = painterResource(id = R.drawable.ic_date_range),
                        title = "DueDate",
                        date = allDetailViewModel.formatDate(selectedDate),
                        iconEnd = painterResource(id = R.drawable.ic_arrow)
                    )
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                showTimerPicker = true
                            }
                        )
                ){
                    GroupRowUi(
                        iconStart = painterResource(id = R.drawable.ic_sharp_timer),
                        title = "Time",
                        date = allDetailViewModel.formatTime(selectedTime),
                        iconEnd = painterResource(id = R.drawable.ic_arrow)
                    )
                }
                if (showDatePicker){
                    val datePickerState = rememberDatePickerState()

                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    selectedDate = datePickerState.selectedDateMillis
                                    allDetailViewModel.setDate()
                                    selectedDate?.let {
                                        onSelectedDate(it)   // ✅ send outside
                                    }
                                    showDatePicker = false
                                }
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDatePicker = false
                            }) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
                if (showTimerPicker){
                    AlertDialog(
                        onDismissRequest = {showTimerPicker = false},
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    //    create time in Millis
//                                    timerPicker
//                                    timerPicker?.let {
//                                        onSelectedTimer
//                                    }
                                    val calendar = Calendar.getInstance().apply {
                                        set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                        set(Calendar.MINUTE, timePickerState.minute)
                                        set(Calendar.SECOND, 0)
                                    }

                                    val timeMillis = calendar.timeInMillis

                                    allDetailViewModel.setTimer(timeMillis)
                                    allDetailViewModel.selectedTimerDate?.let {
                                        onSelectedTimer(timeMillis)
                                    }
                                    showTimerPicker = false
                                }
                            ) {
                                Text(
                                    text = "Confirm"
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {}
                            ) {
                                Text(
                                    text = "Cancel"
                                )
                            }
                        },
                        text = {
                            TimeInput(
                                state = timePickerState
                            )
                        }
                    )
                }
            }
        }
    }
}

fun formatTime(millis:Long?): String{
    if (millis == null) return "No time Selected"
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(Date(millis))
}