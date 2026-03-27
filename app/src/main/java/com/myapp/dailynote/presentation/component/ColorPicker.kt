package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.dailynote.R

@Composable
fun ColorPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
){

    val colors = listOf(
        Color(0xFF2196F3),
        Color(0xFF9C27B0),
        Color(0xFFF44336),
        Color(0xFFFF9800),
        Color(0xFFFFC107),
        Color(0xFF4CAF50),
        Color(0xFF03A9F4)

    )

    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Colors",
                fontSize = 12.sp
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_question_exchange),
                contentDescription = null,
                modifier = Modifier
                    .size(14.dp)
            )
        }
        Spacer(Modifier.height(12.dp))

        LazyRow {
            items(colors){color ->
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(40.dp) // outer size
                        .clip(CircleShape)
                        .border(
                            width = if (selectedColor == color) 2.dp else 0.dp,
                            color = if (selectedColor == color) color else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            onColorSelected(color)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp) // inner color
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
        }
    }
}