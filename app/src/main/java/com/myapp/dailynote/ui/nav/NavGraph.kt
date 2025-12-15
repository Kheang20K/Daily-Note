package com.myapp.dailynote.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapp.dailynote.ui.screen.AddScreen
import com.myapp.dailynote.ui.screen.MainScreen

@Composable
fun NavGraphs(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            MainScreen(navController = navController)
        }
        composable("add_screen"){
            AddScreen(navController = navController)
        }
    }

}