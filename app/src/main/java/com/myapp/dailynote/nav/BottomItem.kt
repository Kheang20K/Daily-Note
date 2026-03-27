package com.myapp.dailynote.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class ScreenRoute(val route: String,val title:String,val icon: ImageVector){
    object Home: ScreenRoute("home_screen", "Home", icon = Icons.Default.Home)
    object Calender: ScreenRoute("calender_screen","Calender", icon = Icons.Default.DateRange)
    object Setting: ScreenRoute("setting_screen","Settings", icon = Icons.Default.Settings)
}