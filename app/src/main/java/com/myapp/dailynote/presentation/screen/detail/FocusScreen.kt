package com.myapp.dailynote.presentation.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.R
import com.myapp.dailynote.data.TimerState
import com.myapp.dailynote.presentation.component.AlertDiaLogEnd
import com.myapp.dailynote.presentation.component.FocusBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusScreen(
    navController: NavController,
    viewModel: AllDetailViewModel = hiltViewModel()
){
    var showDialog by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Focus"
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(
                                onClick = {
                                    if (viewModel.timerState == TimerState.RUNNING){
                                        showDialog = true
                                    }else{
                                        navController.popBackStack()
                                    }
                                }
                            )
                    )
                },
            )
        }
    ){paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
//                .padding(top = 60.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ){
            TimeScreen()
        }
        if (showDialog){
            AlertDiaLogEnd(
                onDismissRequest = {
                    showDialog = false
                },
                onConfirmation = {
                    showDialog = false
//                    timerState = TimerState.IDLE
                    viewModel.endTimer()
                    navController.popBackStack()
                },
                dialogTitle = {
                    Text(
                        text = "Waring",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                },
                dialogText = {
                    Text(
                        text = "Are you sure you want to end the focus timer?",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                },
                icon = Icons.Default.Warning
            )
        }
    }
}

@Composable
fun IndeterminateCircularIndicator(){
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope
    var inputTime by remember { mutableStateOf("") }
    val totalTime = inputTime.toLongOrNull()?.times(1000L) ?: 0L

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }

}


@Composable
fun TimeScreen(
    viewModel: AllDetailViewModel = hiltViewModel()
){
    var showBottomSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .clickable(
                    onClick = {
                        showBottomSheet = true
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = if(viewModel.timerState == TimerState.IDLE){
                    formatTime(viewModel.selectedMinute)
                }else{
                    formatSeconds(viewModel.remainingSeconds)
                },
                fontSize = 65.sp
            )
            Spacer(Modifier.width(16.dp))
            if (viewModel.timerState == TimerState.IDLE){
                Icon(
                    painter = painterResource(id = R.drawable.edit_timer),
                    contentDescription = "edit Timer",
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .weight(0.5f)
//                    .width(120.dp)
                    .height(42.dp),
                onClick = {
                    when (viewModel.timerState){
                        TimerState.IDLE -> {
                            viewModel.startTimer()
                        }

                        TimerState.RUNNING -> {
                            viewModel.pauseTimer()
                        }
                        TimerState.PAUSED -> {
                            viewModel.resumeTimer()
                        }
                        TimerState.FINISHED -> {
                            viewModel.resetTimer()
                        }
                    }
                }
            ) {
                Text(
                    when (viewModel.timerState) {
                        TimerState.IDLE -> "Start"
                        TimerState.RUNNING -> "Pause"
                        TimerState.PAUSED -> "Resume"
                        TimerState.FINISHED -> "Done"
                    }
                )
            }
//            Spacer(Modifier.width(16.dp))

            if (viewModel.timerState == TimerState.RUNNING || viewModel.timerState == TimerState.PAUSED) {
                Button(
                    modifier = Modifier
                        .weight(0.5f)
//                        .width(120.dp)
                        .height(42.dp),
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Text("End Timer")
                }
            }
        }
        FocusBottomSheet(
            showBottomSheet = showBottomSheet,
            onDismiss = {showBottomSheet = false},
            onSaveMinutes = {
                viewModel.setMinute(it)
                showBottomSheet = false
            }
        )

//        Column (
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ){
//            CounterTimer(
//                remainingTime = viewModel.remainingSeconds * 1000L,
//                totalTime = viewModel.selectedMinute * 60 *1000L,
//                isRunning = viewModel.timerState == TimerState.RUNNING,
//                handleColor = Color.LightGray,
//                inactiveBarColor = Color.LightGray,
//                activeBarColor = Color.Green,
//                modifier = Modifier.size(250.dp),
//                onToggle = {
//                    when(viewModel.timerState){
//                        TimerState.IDLE -> viewModel.startTimer()
//                        TimerState.RUNNING -> viewModel.pauseTimer()
//                        TimerState.PAUSED -> viewModel.resumeTimer()
//                        TimerState.FINISHED -> viewModel.resetTimer()
//                    }
//                }
//            )
//        }


        if (showDialog){
            AlertDiaLogEnd(
                onDismissRequest = {
                    showDialog = false
                },
                onConfirmation = {
                    showDialog = false
                    viewModel.timerState = TimerState.IDLE
                    viewModel.endTimer()
                },
                dialogTitle = {
                    Text(
                        text = "Waring",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp
                    )
                },
                dialogText = {
                    Text(
                        text = "Are you sure you want to end the focus timer?",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                },
                icon = Icons.Default.Warning
            )
        }
    }
}


fun formatSeconds(seconds: Int): String {
    val h = seconds / 3600
    val m = (seconds % 3600) / 60
    val s = seconds % 60

    return "%02d:%02d:%02d".format(h, m, s)
}

fun formatTime(minutes: Int): String {
    val h = minutes / 60
    val m = minutes % 60

    return if (h > 0) {
        "${h}h ${m}m"
    } else {
        "${m}m"
    }
}

