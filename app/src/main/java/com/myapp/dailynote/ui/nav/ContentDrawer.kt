package com.myapp.dailynote.ui.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ContentDrawer(
    onNavigate: (String) -> Unit

){
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(Color.White)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Blue)

        ){  }
        NavigationDrawerItem(
            label = { Text("All tasks") },
            selected = false,
            onClick = { onNavigate(Screen.Home.route) },
            icon = { Icon(Icons.Filled.Home, null) }
        )
        NavigationDrawerItem(
            label = { Text("Daily") },
            selected = false,
            onClick = { onNavigate(Screen.Daily.route) },
            icon = { Icon(Icons.Filled.Place, null) }
        )
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Daily : Screen("daily")
    object Settings : Screen("settings")
    object Add : Screen("add_screen")
}