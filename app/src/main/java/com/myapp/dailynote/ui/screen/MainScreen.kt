package com.myapp.dailynote.ui.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.data.entities.NoteDataUser
import com.myapp.dailynote.data.viewmodel.NoteViewModel
import com.myapp.dailynote.ui.component.UiListTodo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navController: NavController
){
    val notes by viewModel.notes.collectAsState()
    var title by remember { mutableStateOf("") }
    var context by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    //sheetState
    val sheetState = rememberModalBottomSheetState ()
    var showSheet by remember { mutableStateOf(false) }


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Daily Note") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    showSheet = true
                    navController.navigate("add_screen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){ innerPadding ->

        Column (
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.height(12.dp))

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
                        checkBox = note.isDone,
                        onCheckChange = {checked ->
                            viewModel.upDateNote(note.copy(isDone = checked))
                        },
                        onClick = {
                            navController.navigate("add_screen/${note.id}")
                        }
                    )
                }
            }
        }

    }
}