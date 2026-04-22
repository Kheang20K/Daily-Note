package com.myapp.dailynote.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.component.DatePickerModal
import com.myapp.dailynote.presentation.component.GroupRowUi
import com.myapp.dailynote.presentation.component.NoteAndPhotoGroup
import com.myapp.dailynote.presentation.component.TextEditModern
import com.myapp.dailynote.presentation.screen.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailEditScreen(
    navController: NavController,
    id:Int,
    viewModel: HomeViewModel = hiltViewModel(),
    allViewModel: AllDetailViewModel = hiltViewModel()
){
    val todolistItem = viewModel.todos.collectAsState()
    val todo = todolistItem.value.find { it.id == id }

    var showDatePicker by remember { mutableStateOf(false) }
    var dueDate by remember { mutableStateOf<Long?>(null) }

    var text by remember { mutableStateOf("") }
    LaunchedEffect(todo) {
        if (todo != null){
            text = todo.title
            dueDate = todo.dueDate
        }
    }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Detail Edit",
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable{
                                navController.popBackStack()
                            }
                            .size(32.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable{
                                viewModel.updateTitle(id,text,dueDate)
                                navController.popBackStack()
                            }
                            .size(32.dp),
                        tint = Color(0xFF2BBE66)
                    )
                }
            )
        }
    ){innerPadding->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp),
        ){

            TextEditModern(
                value = text,
                onChangeValue = {newText ->
                    text = newText
                }
            )

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(Color.White)
            ){
                Column (
                    modifier = Modifier
                        .padding(12.dp)
                ){
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
                            date = allViewModel.formatDate(dueDate),
//                            date = convertMillisToDate(onSelectedDate),
                            iconEnd = painterResource(id = R.drawable.ic_arrow)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    GroupRowUi(
                        iconStart = painterResource(id = R.drawable.ic_sharp_timer),
                        title = "Time",
                        date = "dasd",
                        iconEnd = painterResource(id = R.drawable.ic_arrow)
                    )
                    Spacer(Modifier.height(16.dp))
                    GroupRowUi(
                        iconStart = painterResource(id = R.drawable.ic_date_range),
                        title = "Remember at",
                        date = "dsa",
                        iconEnd = painterResource(id = R.drawable.ic_arrow)
                    )
                    Spacer(Modifier.height(16.dp))
                    GroupRowUi(
                        iconStart = painterResource(id = R.drawable.ic_date_range),
                        title = "Repeat",
                        date = "asd",
                        iconEnd = painterResource(id = R.drawable.ic_arrow)
                    )
                }
                if (showDatePicker){
                    DatePickerModal(
                        onDateSelected = { date->
                            dueDate = date
                            showDatePicker = false

                        },
                        onDismiss = {
                            showDatePicker = false
                        }
                    )
                }
            }

//            RememberGroup()

            Spacer(Modifier.height(12.dp))

            NoteAndPhotoGroup()
            Button(
                onClick = {
                    viewModel.updateTitle(id,text,dueDate)
                }
            ) { }
        }
    }
}