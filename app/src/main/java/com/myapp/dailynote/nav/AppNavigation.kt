package com.myapp.dailynote.nav
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.myapp.dailynote.presentation.screen.detail.DetailEditScreen
import com.myapp.dailynote.presentation.screen.detail.FocusScreen
import com.myapp.dailynote.presentation.screen.detail.ManageCategoryScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = "main"
    ) {

        composable("main") {
            MainScreen(navController)
        }

        composable("manage_category",
            enterTransition = {
                slideInHorizontally { it } + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally { -it } + fadeOut()

            },

            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                fadeOut()
            }
        ) {
            ManageCategoryScreen(navController)
        }

        composable("detail_edit/{id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType}),
            enterTransition = {
                slideInHorizontally { it } + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally { -it } + fadeOut()

            },

            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                fadeOut()
            }
        ) {backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailEditScreen(navController, id = id)
        }

        composable("focus_timer",
            enterTransition = {
                slideInHorizontally { it } + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally { -it } + fadeOut()

            },

            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                fadeOut()
            }
        ) {backStackEntry ->
            FocusScreen(
                navController
            )
        }

    }
}