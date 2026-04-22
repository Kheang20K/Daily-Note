package com.myapp.dailynote.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.dailynote.R
import com.myapp.dailynote.presentation.screen.detail.AllDetailViewModel
import com.myapp.dailynote.presentation.screen.home.HomeViewModel

data class filterItem(
    val title: String
)
@Composable
fun CategoryTabFilters(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    viewModelMange: AllDetailViewModel = hiltViewModel()

){
    val folder by viewModelMange.folders.collectAsState()
    val todo by viewModel.todos.collectAsState()
    var selectIndex by remember { mutableStateOf(-1) }
    val selectedId by viewModel.selectedFolderId.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
    ) {
        Box (
            modifier = Modifier
                .weight(0.8f)
                .height(36.dp),

        ){
            LazyRow (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){

                items(folder) { item ->
                    ChipUi(
                        title = item.folderName,
                        isSelected = selectedId == item.folderId,
                        onClick = {
                            viewModel.selectFolder(item.folderId)
                        }
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_category),
            contentDescription = "category_icon",
            modifier = Modifier
                .weight(0.1f)
                .height(26.dp)
                .clickable(
                    onClick = {
                        navController.navigate("manage_category")
                    }
                )
        )
    }
//        Box (
//            modifier = Modifier
//                .weight(0.1f)
//                .height(26.dp)
//                .clickable(
//                    onClick = {
//                        navController.navigate("manage_category")
//                    }
//                )
//        ){
//            Row (
//                modifier = Modifier
//                    .fillMaxSize(),
////                horizontalArrangement = Arrangement.Center,
////                verticalAlignment = Alignment.CenterVertically
//            ){
//                Image(
//                    painter = painterResource(id = R.drawable.ic_category),
//                    contentDescription = "category_icon",
//                    modifier = Modifier
//                        .size(26.dp)
//                )
//            }
//
//        }


}

@Composable
fun ChipUi(
    title : String,
    isSelected : Boolean,
    onClick:  () -> Unit,
    modifier: Modifier = Modifier
){

    val backGroundColor = if (isSelected){
        Color(0xFF7085F5)
    }else{
        Color(0xFFEEEEEE)
    }

    Box(
        modifier = modifier
            .height(26.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(
                color = backGroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(
                onClick = {
                    onClick()
                }
            )
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = title,
            fontSize = 16.sp
        )

    }
}
