package com.myapp.dailynote.ui.component

import android.media.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UiListTodo(
    title: String,
    checkBox: Boolean,
    dueDate: String,
    time: String,
    onCheckChange: (Boolean) -> Unit,
    onClick: () -> Unit,
    deleteTodo: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = checkBox,
                    onCheckedChange = onCheckChange
                )
                Spacer(Modifier.width(12.dp))
                Column (
                    modifier = Modifier
                        .clickable{
                            onClick()
                        },
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = title,
                        maxLines = 1,
                        style = TextStyle(
                            textDecoration =
                                if (checkBox) TextDecoration.LineThrough
                                else TextDecoration.None
                        ),
                        fontWeight = if (checkBox) FontWeight.Bold else FontWeight.Normal,
                        )
                    Spacer(Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Gray
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = dueDate,
                            color = Color.Gray
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = time,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Column (
                    modifier = Modifier
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ){
                    Text(
                        text = "DAYS",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "$",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                Spacer(Modifier.width(8.dp))
                Column (
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .padding(end = 8.dp)
                        .clickable(
                            onClick = deleteTodo
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }



            }

        }
    }
}
@Preview
@Composable
fun previewUiListTodo(){
    UiListTodo(
        title = "Title",
        checkBox = true,
        dueDate = "Due Date",
        time = "Time",
        onCheckChange = {},
        onClick = {},
        deleteTodo = {}
    )
}
