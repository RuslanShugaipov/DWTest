package com.example.dwtest.feature.home.router

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.dwtest.core.ui.theme.animation.DefaultAnimations
import com.example.dwtest.feature.home.ui.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreen(
    navigateToDetailScreen: (String) -> Unit,
) {
    composable<HomeRoute>(
        enterTransition = {
            DefaultAnimations.enterLeft
        },
        exitTransition = {
            DefaultAnimations.exitLeft
        },
    ) {
        HomeScreen(
            onAppClick = { packageName ->
                navigateToDetailScreen(packageName)
            }
        )
    }
}
