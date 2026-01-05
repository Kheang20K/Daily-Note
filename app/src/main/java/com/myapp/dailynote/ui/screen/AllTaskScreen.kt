package com.myapp.dailynote.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.data.entities.NoteDataUser
import com.myapp.dailynote.data.viewmodel.NoteViewModel
import com.myapp.dailynote.ui.component.DialogConfirm
import com.myapp.dailynote.ui.component.UiListTodo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTaskScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navController: NavController,
    openDrawer: () -> Unit
){
    val notes by viewModel.notes.collectAsState()
    var showDialogDelete by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<NoteDataUser?>(null) }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("All tasks") },
                navigationIcon = {
                    IconButton(
                        onClick = openDrawer
                    ) {
                        Icon(
                            Icons.Default.Menu, contentDescription = "menu"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_screen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                snackBarHostState
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(6.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "My days",
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    top = 8.dp,
                    bottom = 16.dp
                )
            ) {
                items(notes){note ->
                    UiListTodo(
                        title = note.title,
                        dueDate = viewModel.getDueDateText(note.dueDateMillis ?: 0),
                        time = note.time,
                        checkBox = note.isDone,
                        onCheckChange = {checked ->
                            viewModel.upDateNote(note.copy(isDone = checked))
                        },
                        onClick = {
                            navController.navigate("add_screen/${note.id}")
                        },
                        deleteTodo = {
                            selectedNote = note
                            showDialogDelete = true
                        }

                    )
                }
            }
            if (showDialogDelete && selectedNote !=null){
                DialogConfirm (
                    onConfirmation = {
                        showDialogDelete = false
                        selectedNote?.let { noteToDelete ->
                            viewModel.deleteNote(noteToDelete)
                            scope.launch {
                                val result = snackBarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo",
                                    duration = SnackbarDuration.Short
                                )
                                if (result == SnackbarResult.ActionPerformed){
                                    viewModel.insertNote(noteToDelete)
                                }
                            }
                            selectedNote = null
                        }
                    },
                    onDismissRequest = {
                        showDialogDelete =false
                        selectedNote= null
                    },
                    title = { Text("Delete Tasks") },
                    text = {Text("This action cannot be undone.")},
                    destructive = true
                )
            }
        }
    }
}