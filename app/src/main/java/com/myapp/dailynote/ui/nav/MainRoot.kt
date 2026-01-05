package com.myapp.dailynote.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainRoot(){
    val navController = rememberNavController()
    NavDrawer(navController = navController)
}