package com.myapp.dailynote.presentation.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.presentation.component.BottomSheet
import com.myapp.dailynote.presentation.component.CardUserCategory
import com.myapp.dailynote.presentation.screen.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCategoryScreen(
    navController: NavController,
    viewModel: ManageViewModel = hiltViewModel()
){
    var showBottomSheet by remember { mutableStateOf(false) }
    val folders by viewModel.folders.collectAsState()


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Create Task") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(
                                onClick = {
                                    navController.popBackStack()
                                }
                            )
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 12.dp, end = 12.dp)
            ){
                LazyColumn {
                    items(folders){folder ->
                        CardUserCategory(
                            iconStart = folder.iconPicker,
                            textStart = folder.folderName,
                            color = folder.colorPicker,
                            onClick = {
                                viewModel.deleteTodo(folder.folderId)
                            }
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
    BottomSheet(
        showBottomSheet = showBottomSheet,
        onDismiss = {showBottomSheet = false}
    )
}
