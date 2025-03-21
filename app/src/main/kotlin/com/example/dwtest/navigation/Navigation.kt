package com.example.dwtest.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.dwtest.feature.detail.router.detailScreen
import com.example.dwtest.feature.detail.router.navigateToDetailScreen
import com.example.dwtest.feature.home.router.HomeRoute
import com.example.dwtest.feature.home.router.homeScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
        ) {
            homeScreen(
                navigateToDetailScreen = { packageName ->
                    navController.navigateToDetailScreen(
                        packageName = packageName
                    )
                }
            )

            detailScreen()
        }
    }
}
