package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.dailynote.R

@Composable
fun CardTodoList(
    onChecked: Boolean,
    title: String,
//    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
){
    var onCheckedChange by remember { mutableStateOf(false) }
    Box(
        modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 14.dp, end = 14.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                modifier
                    .width(32.dp)
            ){
                Checkbox(
                    checked = onCheckedChange,
                    onCheckedChange = {onCheckedChange = it}
                )
            }
            Spacer(modifier.width(12.dp))
            Column (
                modifier
                    .width(220.dp)
            ){
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                )
                Spacer(modifier.height(6.dp))
                Text(
                    text = "23/03",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_flage),
                contentDescription = null,
                modifier
                    .size(28.dp)
            )
        }
    }
}
