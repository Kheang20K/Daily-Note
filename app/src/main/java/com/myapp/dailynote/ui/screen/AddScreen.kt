package com.myapp.dailynote.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.data.database.NoteDataUser
import com.myapp.dailynote.data.model.SubTaskUi
import com.myapp.dailynote.data.viewmodel.DateTimeViewModel
import com.myapp.dailynote.data.viewmodel.NoteViewModel
import com.myapp.dailynote.ui.component.DueDate
import com.myapp.dailynote.ui.component.Reminder
import com.myapp.dailynote.ui.component.formatDate
import com.myapp.dailynote.ui.component.formatTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    dateViewModel: DateTimeViewModel = hiltViewModel(),
    todoTextFromDb: String? = null,
    navController: NavController
){
    var todoText by remember { mutableStateOf(todoTextFromDb ?:"") }
    val isEditing = todoTextFromDb != null

    var showDialog by remember { mutableStateOf(false) }
    var subtask by remember { mutableStateOf("") }

    val subtasks = remember { mutableStateListOf<SubTaskUi>() }

    var reminderDetailMillis by remember { mutableStateOf<Long?>(null) }
    var reminderHour by remember { mutableStateOf<Int?>(null) }
    var reminderMinute by remember { mutableStateOf<Int?>(null) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(if (isEditing) "Edit Task" else "Create Task")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "back",
                            modifier = Modifier
                                .size(34.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.insertNote(
                        NoteDataUser(
                            title = todoText,
                            content = todoText,
                            date = reminderDetailMillis?.let { formatDate(it) } ?: "",
                            time = if (reminderHour != null && reminderMinute != null)
                                formatTime(reminderHour!!, reminderMinute!!)
                            else ""
                        )
                    )
                    if (reminderDetailMillis != null && reminderHour != null && reminderMinute != null){
                        dateViewModel.saveReminder(
                            dateMillis = reminderDetailMillis!!,
                            hour = reminderHour!!,
                            minute = reminderMinute!!,
                            title = todoText,
                            message = todoText
                        )
                    }
                    navController.popBackStack()
                    Log.d("addscreen","${todoText}")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    modifier = Modifier
                        .size(28.dp)
                )
            }

        }
    ){innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ){
            OutlinedTextField(
                value = todoText,
                onValueChange = {todoText = it},
                label = { Text("What needs to be done?") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Subtasks",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(12.dp))
            subtasks.forEachIndexed { index, task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = { check->
                            subtasks[index] = subtasks[index].copy(isDone = check)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = task.title,
                        style = TextStyle(
                            textDecoration = if (task.isDone)
                                TextDecoration.LineThrough
                            else
                                TextDecoration.None,

                            color = if (task.isDone)
                                Color.Black
                            else
                                Color.Black,

                            fontSize = 16.sp
                        )

                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            showDialog = true
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    modifier = Modifier
                        .size(28.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    "Add Subtask",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
            Spacer(Modifier.height(12.dp))
            DueDate()
            Spacer(Modifier.height(12.dp))
            Reminder(
                onDateTimeSelected = { dateMillis, hour, minute ->
                    reminderDetailMillis = dateMillis
                    reminderHour = hour
                    reminderMinute = minute
                }
            )
            if (showDialog){
                DialogInput(
                    onDismissRequest = {
                        showDialog = false
                        subtask = ""
                    },
                    onConfirmation = {
                        if (subtask.isNotBlank()) {
                            subtasks.add(
                                SubTaskUi(title = subtask)
                            )
                        }
                        showDialog = false
                        subtask = ""
                    },
                    title = {
                        Text(
                            text = "Add Subtask",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    textField = {
                        TextField(
                            value = subtask,
                            onValueChange = { subtask = it },
                            placeholder = { Text("New Subtask") },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    },
                )
            }
        }
    }
}
@Composable
fun DialogInput(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: @Composable () -> Unit,
    textField: @Composable (() -> Unit),
){
    AlertDialog(
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

