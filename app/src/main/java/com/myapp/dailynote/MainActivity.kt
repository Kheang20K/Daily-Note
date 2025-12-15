package com.myapp.dailynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import com.myapp.dailynote.ui.nav.NavGraphs
import com.myapp.dailynote.ui.screen.AddScreen
import com.myapp.dailynote.ui.theme.DailyNoteTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyNoteTheme {
//                MainScreen()
                NavGraphs()
            }
        }
    }
}

