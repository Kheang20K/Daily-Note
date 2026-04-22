package com.myapp.dailynote.presentation.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.component.ActionsRow
import com.myapp.dailynote.presentation.component.AlertDiaLogEnd
import com.myapp.dailynote.presentation.component.CategoryTabFilters
import com.myapp.dailynote.presentation.component.HomeBottomSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onMenuClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
){
    val todo by viewModel.filteredTodo.collectAsState()
    val selectedId by viewModel.selectedFolderId.collectAsState()
    val revealedCardIds by viewModel.revealedItemId.collectAsState()


    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var selectedTimer by remember { mutableStateOf<Long?>(null) }
    var deleteItemId by remember { mutableStateOf<Int?>(null) }



    var onChecks by remember { mutableStateOf(false) }

    val ACTION_ITEM_SIZE = 50
    val CARD_OFFSET = 240
    val cardOffsetPx = with(LocalDensity.current) { CARD_OFFSET.dp.toPx() }
    val offsetX = remember { Animatable(0f) }


    var text by remember { mutableStateOf("") }
    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet){
            text = ""

        }
    }
    Scaffold (
        topBar ={
            TopAppBar(
                windowInsets = WindowInsets(),
                title = {},
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_hori),
                            contentDescription = "moreVert",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onMenuClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "search",
                            modifier = Modifier
                                .size(32.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                    text = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                .pointerInput(Unit){
                    detectTapGestures {
                        viewModel.closeAllItems()
                    }
                }
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ){
                AllClick(
                    onSelected = selectedId == null,
                    onClick = {
                        viewModel.selectFolder(null)
                    }
                )
                Spacer(Modifier.width(12.dp))
                CategoryTabFilters(navController)
            }
            HorizontalDivider(thickness = 1.dp)
            Spacer(Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                items(todo, key = {it.id}) { items ->
                    Spacer(Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        val context = LocalContext.current
                        DraggableCardComplex(
                            title = items.title,
                            date = items.dueDate,
                            onChecked = onChecks,
                            onClick = {
                                navController.navigate("detail_edit/${items.id}")
                                Log.d("Todo", items.title)
                            },
                            onClickFlag = {
                                Toast.makeText(context, "On flag", Toast.LENGTH_LONG).show()
                            },
                            isRevealed = revealedCardIds == items.id,
                            cardOffset = cardOffsetPx,
                            onExpand = {
                                viewModel.onItemExpanded(items.id)
                            },
                            onCollapse = {
                                viewModel.onItemCollapsed(items.id)
                            },
                        ) { progress ->
                            ActionsRow(
                                actionIconSize = ACTION_ITEM_SIZE.dp,
                                onDelete = {
                                    deleteItemId = items.id
                                    Log.d("Delete","${items.id}")
                                },
                                onEdit = {},
                                onFavorite = {},
                                onFocus = {
                                    navController.navigate("focus_timer")
                                },
                                progress = progress
                            )
                        }
                    }
                    if (deleteItemId != null) {
                        AlertDiaLogEnd(
                            onDismissRequest = {
                                deleteItemId = null
                            },
                            onConfirmation = {
                                deleteItemId?.let { id->
                                    viewModel.deleteTodoAndClose(id)
                                }
                                deleteItemId = null
                                Log.d("DELETE", "Deleting id = $deleteItemId")
                            },
                            dialogTitle = {
                                Text(
                                    text = "Delete Task?",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 22.sp
                                )
                            },
                            dialogText = {
                                Text(
                                    text = " Are you sure you want to delete task '${items.title}' ?",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            },
                            icon = Icons.Default.Warning
                        )
                    }
                }
            }
        }
    }
    HomeBottomSheet(
        showBottomSheet = showBottomSheet,
        onDismiss = {showBottomSheet = false},
        text = text,
        onTextChange = {text = it},
        onClickable = { title,_ ->
            viewModel.insertTodo(
                title = title,
                dueDate = selectedDate
            )
            showBottomSheet = false
            text = ""
        },
        onSelectedDate = {
            selectedDate =it
        },
        onSelectedTimer = {
            selectedTimer = it
        }

    )

}



@Composable
fun AllClick(
    onSelected : Boolean,
    onClick: () -> Unit
){

    Column (
        modifier = Modifier
            .width(24.dp)
            .clickable{
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = "All",
            fontSize = 18.sp
        )
        Divider(thickness = 1.dp)
    }

}