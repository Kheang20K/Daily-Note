package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GroupRowUi(
    iconStart: Painter,
    title: String,
    date: String,
    iconEnd: Painter,

    ){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = iconStart,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 17.sp,
                color = Color.Black,
                fontStyle = FontStyle.Normal

            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = date,
                fontSize = 13.sp,
                color = Color.Black
            )
            Spacer(Modifier.width(12.dp))
            Icon(
                painter = iconEnd,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp),
                tint = Color.LightGray
            )
        }
    }
}