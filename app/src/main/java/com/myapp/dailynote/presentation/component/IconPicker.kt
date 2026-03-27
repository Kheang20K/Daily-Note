package com.myapp.dailynote.presentation.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.dailynote.R
import com.myapp.dailynote.domain.model.IconData

@Composable
fun IconPicker(
    selectedColor: Color,
    selectedIcon: Int?,
    onIconSelected: (Int) -> Unit
){
    val icons = listOf(
        R.drawable.ic_folder,
        R.drawable.ic_bag,
        R.drawable.ic_tv,
        R.drawable.ic_cake,
        R.drawable.ic_cafe,
        R.drawable.ic_note,
        R.drawable.ic_fav,
        R.drawable.ic_movie,
        R.drawable.ic_cart,
        R.drawable.ic_book,
        R.drawable.ic_flage,
        R.drawable.ic_plane,
        R.drawable.ic_food,
        R.drawable.ic_fitness_1,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        items(icons) { iconRes ->

            Box(
                modifier = Modifier
                    .padding(end = 8.dp, top = 8.dp)
                    .clip(CircleShape)
                    .clickable {
                        onIconSelected(iconRes) // ✅ REAL drawable id
                    },
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(
                            if (selectedIcon == iconRes)
                                selectedColor
                            else
                                Color.LightGray.copy(alpha = 0.2f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = iconRes), // ✅ correct
                        contentDescription = null,
                        tint = if (selectedIcon == iconRes) Color.White else Color.Gray
                    )
                }
            }
        }
//        items(icons.size){iconRes ->
//            Box(
//                modifier = Modifier
////                    .size(55.dp)
//                    .padding(end = 8.dp, top = 8.dp)
//                    .clip(CircleShape)
//                    .clickable{
//                        onIconSelected(iconRes)
//                    },
//                contentAlignment = Alignment.Center
////                    .border()
//            ){
//                Box(
//                    modifier = Modifier
//                        .size(44.dp) // inner color
//                        .clip(CircleShape)
//                        .background(
//                            if (selectedIcon == iconRes)
//                                selectedColor
//                            else
//                                Color.LightGray.copy(alpha = 0.2f)
//                        ),
//                    contentAlignment = Alignment.Center
//                ){
//                    Icon(
//                        painter = painterResource(id = icons[iconRes]) ,
//                        contentDescription = null,
//                        tint = if (selectedIcon == iconRes) Color.White else Color.Gray
//                    )
//                }
//
//            }
//        }
    }
}
