package com.myapp.dailynote.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Composable
//fun NavDrawerHome(){
//    val scope = rememberCoroutineScope()
//    val drawerScope = rememberDrawerState(initialValue = DrawerValue.Closed)
//
//    ModalNavigationDrawer(
//        drawerContent = {
//            ModalDrawerSheet {
//                Column (
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp)
//                        .verticalScroll(rememberScrollState())
//                ){
//                    Text(
//                        text = "Todo List",
//                        fontSize = 26.sp
//                    )
//                }
//            }
//        }
//    ) { }
//}