package com.myapp.dailynote.ui.screen

import android.widget.Toast
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.data.entities.NoteDataUser
import com.myapp.dailynote.data.model.SubTaskUi
import com.myapp.dailynote.data.viewmodel.DateTimeViewModel
import com.myapp.dailynote.data.viewmodel.NoteViewModel
import com.myapp.dailynote.ui.component.DialogInput
import com.myapp.dailynote.ui.component.DueDate
import com.myapp.dailynote.ui.component.Reminder
import com.myapp.dailynote.ui.component.formatDate
import com.myapp.dailynote.ui.component.formatTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    noteViewModel: NoteViewModel = hiltViewModel(),
    dateViewModel: DateTimeViewModel = hiltViewModel(),
    noteId: Int?,
    navController: NavController
){
    val noteList by noteViewModel.notes.collectAsState()
    val noteToEdit = noteId?.let { id -> noteList.find { it.id == id } }
    val isEditing = noteToEdit != null

    var todoText by remember { mutableStateOf("") }
    var subtask by remember { mutableStateOf("") }
    val subtasks = remember { mutableStateListOf<SubTaskUi>() }

    var dueDateMillis by remember { mutableStateOf<Long?>(null) }
    var dueHour by remember { mutableStateOf<Int?>(null) }
    var dueMinute by remember { mutableStateOf<Int?>(null) }
    var reminderMillis by remember { mutableStateOf<Long?>(null) }
    var reminderHour by remember { mutableStateOf<Int?>(null) }
    var reminderMinute by remember { mutableStateOf<Int?>(null) }

    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(noteToEdit) {
        noteToEdit?.let { note ->
            todoText = note.title
            subtasks.clear()
            subtasks.addAll(note.subtasks ?: emptyList())
            dueDateMillis = note.dueDateMillis
            dueHour = note.dueHour
            dueMinute = note.dueMinute
            reminderMillis = note.reminderDateMillis
            reminderHour = note.reminderHour
            reminderMinute = note.reminderMinute
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Task" else "Create Task") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "back", modifier = Modifier.size(34.dp))
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (todoText.isNotBlank()) {
                        if (noteToEdit != null) {
                            // Update existing note
                            val updatedNote = noteToEdit.copy(
                                title = todoText,
                                content = todoText,
                                subtasks = subtasks.toList(),
                                dueDateMillis = dueDateMillis,
                                dueHour = dueHour,
                                dueMinute = dueMinute,
                                reminderDateMillis = reminderMillis,
                                reminderHour = reminderHour,
                                reminderMinute = reminderMinute,
                                date = reminderMillis?.let { formatDate(it) } ?: noteToEdit.date,
                                time = if (reminderHour != null && reminderMinute != null)
                                    formatTime(reminderHour!!, reminderMinute!!)
                                else noteToEdit.time
                            )
                            noteViewModel.upDateNote(updatedNote)
                        } else {
                            val newNote = NoteDataUser(
                                title = todoText,
                                content = todoText,
                                subtasks = subtasks.toList(),
                                dueDateMillis = dueDateMillis,
                                dueHour = dueHour,
                                dueMinute = dueMinute,
                                reminderDateMillis = reminderMillis,
                                reminderHour = reminderHour,
                                reminderMinute = reminderMinute,
                                date = reminderMillis?.let { formatDate(it) } ?: "",
                                time = if (reminderHour != null && reminderMinute != null)
                                    formatTime(reminderHour!!, reminderMinute!!)
                                else ""
                            )
                            noteViewModel.insertNote(newNote)
                        }
                        if (reminderMillis != null && reminderHour != null && reminderMinute != null) {
                            dateViewModel.saveReminder(
                                dateMillis = reminderMillis!!,
                                hour = reminderHour!!,
                                minute = reminderMinute!!,
                                title = todoText,
                                message = todoText
                            )
                        }
                        Toast.makeText(context, "Your Activity is saved!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            ) {
                Icon(Icons.Default.Check, contentDescription = "save", modifier = Modifier.size(28.dp))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = todoText,
                onValueChange = { todoText = it },
                label = { Text("What needs to be done?") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Subtasks",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column {
                subtasks.forEachIndexed { index, task ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = task.isDone,
                            onCheckedChange = { checked ->
                                subtasks[index] = subtasks[index].copy(isDone = checked)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = task.title,
                            style = TextStyle(
                                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDialog = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "add",
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "Add Subtask",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            DueDate(
                selectedMillis = dueDateMillis,
                selectedHour = dueHour,
                selectedMinute = dueMinute,
                onDateSelected = { dueDateMillis = it },
                onTimeSelected = { hour, minute ->
                    dueHour = hour
                    dueMinute = minute
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Reminder(
                selectedMillis = reminderMillis,
                selectedHour = reminderHour,
                selectedMinute = reminderMinute,
                onDateTimeSelected = { millis, hour, minute ->
                    reminderMillis = millis
                    reminderHour = hour
                    reminderMinute = minute
                }
            )
            if (showDialog) {
                DialogInput(
                    onDismissRequest = {
                        showDialog = false
                        subtask = ""
                    },
                    onConfirmation = {
                        if (subtask.isNotBlank()) {
                            subtasks.add(SubTaskUi(title = subtask))
                        }
                        showDialog = false
                        subtask = ""
                    },
                    title = {
                        Text(
                            "Add Subtask",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    textField = {
                        TextField(
                            value = subtask,
                            onValueChange = { subtask = it },
                            placeholder = { Text("New Subtask") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}
