package com.myapp.dailynote.ui.component


import android.graphics.Color
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UiListTodo(
    title: String,
//    date:String,
    onClick: () -> Unit,
    checkBox: Boolean,
    onCheckChange : (Boolean) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                onClick()
            },
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
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = title,
                        maxLines = 1
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "",
                            modifier = Modifier.size(18.dp),
                            tint = androidx.compose.ui.graphics.Color.Gray
                        )

                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Today",
                            color = androidx.compose.ui.graphics.Color.Gray
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
                        color = androidx.compose.ui.graphics.Color.Gray,
                        fontSize = 16.sp

                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "0",
                        color = androidx.compose.ui.graphics.Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
@Composable
@Preview
fun PreviewUi(){
    UiListTodo(
        title = "Hello",
//        date = "Today",
        checkBox = true,
        onCheckChange ={},
        onClick = {}
    )
}