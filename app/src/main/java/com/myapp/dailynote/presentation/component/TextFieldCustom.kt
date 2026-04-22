package com.myapp.dailynote.presentation.component

import android.health.connect.datatypes.units.Length
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldCustom(
    value : String,
    onChangeValue: (String) -> Unit,
    maxLength: Int = 50,
    labelStr: String,
    modifier: Modifier = Modifier

){

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength){
                onChangeValue(it)
            }
        },
        label = {Text(labelStr)},
        modifier = modifier.fillMaxWidth(),
        supportingText = {
            Row (modifier = modifier.fillMaxWidth()){
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${value.length}/$maxLength",
                    fontSize = 14.sp
                )
            }
        }
    )
}
