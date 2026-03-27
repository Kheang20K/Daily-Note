package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SaveSheet(
    title: String,
    onClose : () -> Unit,
    onSave : () -> Unit,
    iconClose: ImageVector,
    iconCorrect: ImageVector,
    iconCloseColor: Color = Color.Black,
    iconSaveColor: Color = Color.Black,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            imageVector = iconClose,
            contentDescription = null,
            tint = iconCloseColor,
            modifier = Modifier
                .clickable{
                    onClose()
                }
                .size(32.dp)

        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = title,
            fontSize = 21.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.weight(1f))
        Icon(
            imageVector = iconCorrect,
            contentDescription = null,
            tint = iconSaveColor,
            modifier = Modifier
                .clickable{
                    onSave()
                }
                .size(32.dp)
        )
    }
}
