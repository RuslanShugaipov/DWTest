package com.example.dwtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.dwtest.navigation.Navigation
import com.example.dwtest.core.ui.theme.DWTestTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TRANSPARENT_COLOR = android.graphics.Color.TRANSPARENT

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = TRANSPARENT_COLOR,
                darkScrim = TRANSPARENT_COLOR,
            ),
            navigationBarStyle = SystemBarStyle.light(TRANSPARENT_COLOR, TRANSPARENT_COLOR)
        )

        setContent {
            val navController = rememberNavController()

            DWTestTheme {
                Navigation(
                    navController = navController,
                )
            }
        }
    }
}
