package com.example.dwtest.feature.detail.router

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.dwtest.core.ui.theme.animation.DefaultAnimations
import com.example.dwtest.feature.detail.ui.DetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val packageName: String? = null,
)

fun NavGraphBuilder.detailScreen() {
    composable<DetailRoute>(
        enterTransition = {
            DefaultAnimations.enterRight
        },
        exitTransition = {
            DefaultAnimations.exitRight
        },
        popExitTransition = {
            DefaultAnimations.exitRight
        }
    ) {
        DetailScreen()
    }
}

fun NavController.navigateToDetailScreen(
    packageName: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(route = DetailRoute(packageName), navOptions)
}
