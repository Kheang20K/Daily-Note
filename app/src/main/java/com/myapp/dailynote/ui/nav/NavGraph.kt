@file:Suppress("DEPRECATION")

package com.myapp.dailynote.ui.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.myapp.dailynote.ui.screen.AddScreen
import com.myapp.dailynote.ui.screen.AllTaskScreen
import com.myapp.dailynote.ui.screen.DailyScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraphs(
    navController : NavController,
    openDrawer: () -> Unit
){
    AnimatedNavHost(navController = navController as NavHostController, startDestination = "home"){
        composable("home",
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }){
            AllTaskScreen(navController = navController, openDrawer = openDrawer)
        }
        composable(
            route = "add_screen",
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            AddScreen(navController = navController, noteId = null)
        }
        composable(
            route = "add_screen/{noteId}",
            arguments = listOf(navArgument("noteId"){
                type = NavType.IntType
                defaultValue = -1
            }),
        ){backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?:-1
            AddScreen(noteId = if (noteId == -1) null else noteId, navController = navController)
        }
        composable(
            route = "Daily",
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ){
            DailyScreen(navController, openDrawer)
        }
    }
}