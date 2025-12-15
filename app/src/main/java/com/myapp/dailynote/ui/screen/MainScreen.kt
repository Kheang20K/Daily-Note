package com.myapp.dailynote.ui.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.myapp.dailynote.data.database.NoteDataUser
import com.myapp.dailynote.data.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navController: NavController
){
    val notes by viewModel.notes.collectAsState()
    var title by remember { mutableStateOf("") }
    var context by remember { mutableStateOf("") }

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

//        Box(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        ){
//
//        }
//
//        if (showSheet){
//            ModalBottomSheet(
//                sheetState = sheetState,
//                onDismissRequest = {
//                    showSheet = false
//                    title = ""
//                },
//            ) {
//                Column (
//                    modifier = Modifier
//                        .fillMaxHeight(1f)
//                ){
//                    Column (
//                        modifier = Modifier
//                            .fillMaxSize(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        TextField(
//                            value = title,
//                            onValueChange = {title = it},
//                            placeholder = { Text("Enter Title") }
//                        )
//                        TextField(
//                            value = context,
//                            onValueChange = {context = it},
//                            placeholder = { Text("Enter Content") }
//                        )
//                        Spacer(Modifier.height(12.dp))
//                        Button(
//                            onClick = {
//                                val note = NoteDataUser(
//                                    title = title,
//                                    content = context,
//                                    date = "now",
//                                    time ="now",
//                                )
//                                viewModel.insertNote(note)
//                                title = ""
//                                context = ""
//                            }
//                        ) {
//                            Text("Save")
//                        }
//                    }
//                }
//            }
//        }


        Column (
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            TextField(
//                value = title,
//                onValueChange = {title = it},
//                placeholder = { Text("Enter Title") }
//            )
//            TextField(
//                value = context,
//                onValueChange = {context = it},
//                placeholder = { Text("Enter Content") }
//            )
//            Spacer(Modifier.height(12.dp))
//            Button(
//                onClick = {
//                    val note = NoteDataUser(
//                        title = title,
//                        content = context,
//                        date = "now",
//                        time ="now",
//                    )
//                    viewModel.insertNote(note)
//                    title = ""
//                    context = ""
//                }
//            ) {
//                Text("Save")
//            }
            Spacer(Modifier.height(12.dp))
            Text("Notes")

            LazyColumn {
                items(notes){note ->
                    Text(note.title)
                    Text(note.content)
                }
            }
        }

    }
}