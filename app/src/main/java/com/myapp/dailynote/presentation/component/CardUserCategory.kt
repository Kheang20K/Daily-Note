package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.dailynote.R

@Composable
fun CardUserCategory(
    iconStart: Int,
    textStart:String,
    color: Int,
    onClick : () -> Unit
){

    val iconRes = try {
        if (iconStart != 0) iconStart else R.drawable.ic_folder
    } catch (e: Exception) {
        R.drawable.ic_folder
    }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
    ){
        Row (
            modifier = Modifier

                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = iconRes), // default icon
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = Color(color)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = textStart,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_hori),
                    contentDescription = null,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable{
                            onClick()
                        }
                )
            }
        }
    }
}

