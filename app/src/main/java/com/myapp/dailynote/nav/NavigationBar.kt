package com.myapp.dailynote.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.myapp.dailynote.presentation.screen.calender.CalenderScreen
import com.myapp.dailynote.presentation.screen.home.HomeScreen
import com.myapp.dailynote.presentation.screen.setting.SettingScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(rootNavController: NavController) {

    val navController = rememberNavController()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = Modifier.fillMaxHeight(),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.fillMaxWidth(0.8f)
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Todo List",
                        fontSize = 26.sp
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            bottomBar = {
                BottomBar(navController)
            }
        ) { padding ->

            AnimatedNavHost(
                navController = navController,
                startDestination = ScreenRoute.Home.route,
                modifier = Modifier.padding(padding)
            ) {

                composable(
                    ScreenRoute.Home.route,
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
                    HomeScreen(
                        rootNavController,
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }

                        }
                    )
                }

                composable(
                    ScreenRoute.Calender.route,
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
                    CalenderScreen()
                }

                composable(
                    ScreenRoute.Setting.route,
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
                    SettingScreen()
                }

            }

        }
    }
}


@Composable
fun BottomBar(navController: NavController){
    val items = listOf(
        ScreenRoute.Home,
        ScreenRoute.Calender,
        ScreenRoute.Setting
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->

            NavigationBarItem(
                icon = {
                    Icon(screen.icon, contentDescription = screen.title)
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(ScreenRoute.Home.route){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }
                }
            )
        }
    }
}