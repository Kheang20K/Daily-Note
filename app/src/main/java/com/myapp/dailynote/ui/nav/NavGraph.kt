package com.myapp.dailynote.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.dailynote.ui.screen.AddScreen
import com.myapp.dailynote.ui.screen.MainScreen

@Composable
fun NavGraphs(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            MainScreen(navController = navController)
        }
        composable(
            route = "add_screen"
        ) {
            AddScreen(navController = navController)
        }


        composable(
            route = "add_screen/{noteId}",
            arguments = listOf(navArgument("noteId"){
                type = NavType.IntType
                defaultValue = -1
            })
        ){backStackEntry ->
             val noteId = backStackEntry.arguments?.getInt("noteId") ?:-1
            AddScreen(noteId = if (noteId == -1) null else noteId, navController = navController)
        }
    }

}