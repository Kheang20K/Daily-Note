package com.myapp.dailynote.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.myapp.dailynote.data.TimerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusBottomSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    onSaveMinutes: (Int) -> Unit
){

    var selectedMinutes by remember { mutableStateOf(5) }
    var remainingSeconds by remember { mutableStateOf(0) }
    var timerState by remember { mutableStateOf(TimerState.IDLE) }
    val coroutineScope = rememberCoroutineScope ()
    val MIN = 5
    val MAX = 240


    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet){
        ModalBottomSheet(
            containerColor = Color.White, // 👈 force background
            onDismissRequest = {
                onDismiss()
            },
            sheetState = sheetState
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
            ){
                SaveSheet(
                    title = "Select a Duration",
                    onClose = {
                        onDismiss()
                    },
                    onSave = {
                        coroutineScope.launch {
                            sheetState.hide()
                            onSaveMinutes(selectedMinutes)
                            onDismiss()

                        }
                    },
                    iconClose = Icons.Default.Close,
                    iconCorrect = Icons.Default.Check,
                    iconCloseColor = Color.Gray,
                    iconSaveColor = Color(0xFF2BBE66)
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    WheelPicker(
                        range = MIN..MAX,
                        selectedValue = selectedMinutes,
                        onValueChange = {selectedMinutes = it}
                    )
//                    LegacyNumberPicker(
//                        value = selectedMinutes,
//                        range = MIN..MAX,
//                        onChangeValue = {newValue ->
//                            selectedMinutes = newValue.coerceIn(MIN,MAX)
//                        }
//                    )
                }
            }
        }

    }
}
@Composable
fun PickerItem(
    value: Int,
    isSelected: Boolean
) {
    val scale = if (isSelected) 1.3f else 1f

    Text(
        text = value.toString().padStart(2, '0'),
        fontSize = 22.sp,
        modifier = Modifier.scale(scale),
        color = if (isSelected) Color.Black else Color.Gray
    )
}

@SuppressLint("FrequentlyChangingValue")
@Composable
fun WheelPicker(
    range: IntRange,
    selectedValue: Int,
    onValueChange: (Int) -> Unit
) {
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = selectedValue - range.first
    )
    val flingBehavior = rememberSnapFlingBehavior(listState)


    val itemHeight = 50.dp
    val visibleItems = 5
    val centerIndex = visibleItems / 2

    Box(
        modifier = Modifier.height(itemHeight * visibleItems),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(range.count()) { index ->
                val value = range.first + index

                val isSelected =
                    listState.firstVisibleItemIndex + centerIndex == index

                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row (
                    ){
                        PickerItem(value, isSelected)
                        Spacer(Modifier.width(20.dp))
                        Text("Minute")
                    }
                }
            }
        }
        // 🔥 Center highlight line
        Box(
            modifier = Modifier
                .height(itemHeight)
                .fillMaxWidth()
                .border(1.dp, Color.LightGray)
        )
    }

    // 🔥 Detect selected value
    LaunchedEffect(listState.firstVisibleItemIndex) {
        val newValue = range.first + listState.firstVisibleItemIndex + centerIndex
        onValueChange(newValue.coerceIn(range))
    }
}
