package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.dailynote.presentation.screen.detail.AllDetailViewModel

@Composable
fun CardTodoList(
    onChecked: Boolean,
    title: String,
    dueDate: Long?,
//    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AllDetailViewModel = hiltViewModel(),

    ){
    var onCheckedChange by remember { mutableStateOf(false) }
    Row (
        modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier
                .width(32.dp)
        ) {
            Checkbox(
                checked = onCheckedChange,
                onCheckedChange = { onCheckedChange = it }
            )
        }
        Spacer(modifier.width(12.dp))
        Column(
            modifier
                .fillMaxSize()
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
            )
            Spacer(modifier.height(5.dp))
            Text(
                text = viewModel.formatDate(dueDate),
                fontSize = 14.sp
            )
        }
    }
}
