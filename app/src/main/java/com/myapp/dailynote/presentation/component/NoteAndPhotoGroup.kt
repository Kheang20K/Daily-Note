package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.dailynote.R

@Composable
fun NoteAndPhotoGroup(){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .background(Color.White)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column  (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                GroupRowUi(
                    iconStart = painterResource(id = R.drawable.ic_note),
                    title = "Note",
                    date = "",
                    iconEnd = painterResource(id = R.drawable.ic_arrow)
                )
                Spacer(Modifier.height(10.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 42.dp)
                ){
                    Text(
                        text = "",
                    )
                }
            }
            Spacer(Modifier.height(8.dp))

            GroupRowUi(
                iconStart = painterResource(id = R.drawable.ic_attach_file),
                title = "Note",
                date = "",
                iconEnd = painterResource(id = R.drawable.ic_arrow)
            )
        }
    }
}
@Composable
@Preview
fun NotePreview(){
    NoteAndPhotoGroup()
}