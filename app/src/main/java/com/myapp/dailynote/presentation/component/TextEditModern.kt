package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextEditModern(
    value : String,
    onChangeValue: (String) -> Unit,
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .background(Color.White)
    ){
        Column (
            modifier = Modifier
                .padding(12.dp)
        ){
            BasicTextField(
                value = value,
                onValueChange = onChangeValue,
                textStyle = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            )
            Spacer(Modifier.height(75.dp))
            Text(
                text = "Hello"
            )
            Spacer(Modifier.height(75.dp))
            Text(
                text = "+ Add Sub-task",
                fontSize = 12.sp,
                color = Color.Blue
            )

        }
    }

}
