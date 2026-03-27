package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.screen.detail.ManageViewModel
import com.myapp.dailynote.presentation.screen.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    showBottomSheet: Boolean,
    text : String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onClickable: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    viewModelManage: ManageViewModel = hiltViewModel()

){
    val selectedId by viewModel.selectedFolderId.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val folder  by viewModelManage.folders.collectAsState()

    var selectedColor by remember { mutableStateOf(Color(0xFF2196F3)) }
    var selectedIcon by remember { mutableStateOf<Int?>(null)}


    if (showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            sheetState = sheetState
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(start = 16.dp, end = 16.dp, top = 6.dp)
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            onClickable(text)
                        },
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF2BBE66),
                        modifier = Modifier
                            .size(32.dp)
                    )
                }

                Spacer(Modifier.height(12.dp))

                TextFieldCustom(
                    value = text,
                    onChangeValue = onTextChange,
                    maxLength = 50,
                    label = "Input new task here"
                )

                Spacer(Modifier.height(12.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp)
                        .height(35.dp)
                ){
                    Box (
                        modifier = Modifier
                            .weight(0.8f)
                            .height(36.dp)
                    ){
                        LazyRow (
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ){

                            // ✅ Folders
                            items(folder) { item ->
                                ChipUi(
                                    title = item.folderName,
                                    isSelected = selectedId == item.folderId,
                                    onClick = {
                                        viewModel.selectFolder(item.folderId)
                                    },
                                    modifier = Modifier
                                        .height(50.dp)
                                )
                            }
//                            itemsIndexed(folder){ index, item ->
//                                ChipUi(
//                                    title = item.folderName,
//                                    isSelected = selectIndex == index,
//                                    onClick = {
//                                        selectIndex = index
//                                        viewModel.selectFolder(item.folderId)
//                                    }
//                                )
//                            }
                        }
                    }
                }
            }
        }
    }

}