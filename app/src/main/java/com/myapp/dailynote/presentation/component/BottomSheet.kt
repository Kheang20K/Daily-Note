package com.myapp.dailynote.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.screen.detail.AllDetailViewModel
import com.myapp.dailynote.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    viewModelMange: AllDetailViewModel = hiltViewModel()

){
    val sheetState = rememberModalBottomSheetState()
//    var showBottomSheet by remember { mutableStateOf(false) }

    var text by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color(0xFF2196F3)) }
    var selectedIcon by remember { mutableStateOf<Int?>(null)}

    val focusRequester = remember { FocusRequester()}
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet){
            text = ""
            selectedColor = Color(0xFF2196F3)
            selectedIcon = null
        }
        delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    if (showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = {
                onDismiss()
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                    .imePadding()
            ) {
                SaveSheet(
                    title = "Create Your Category",
                    onClose = {
                        onDismiss()
                    },
                    onSave = {
                        viewModelMange.insertFolder(
                            name = text,
                            color = selectedColor.toArgb(),
                            icon = selectedIcon ?: R.drawable.ic_folder
                        )
                        Log.d("COLOR_TEST", selectedColor.toString())
                        onDismiss()
                    },
                    iconClose = Icons.Default.Close,
                    iconCorrect = Icons.Default.Check,
                    iconCloseColor = Color.Gray,
                    iconSaveColor = Color(0xFF2BBE66)
                )

                Spacer(Modifier.height(10.dp))

                TextFieldCustom(
                    value = text,
                    onChangeValue = {text = it},
                    maxLength = 50,
                    labelStr = "Input your category"
                )
                Spacer(Modifier.height(12.dp))

                ColorPicker(
                    selectedColor = selectedColor,
                    onColorSelected = {selectedColor = it}
                )
                Spacer(Modifier.height(12.dp))
                IconPicker(
                    selectedColor = selectedColor,
                    selectedIcon = selectedIcon,
                    onIconSelected = {selectedIcon = it}
                )
            }
        }
    }
}