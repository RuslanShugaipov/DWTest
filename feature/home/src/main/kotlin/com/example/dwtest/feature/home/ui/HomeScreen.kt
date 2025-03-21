package com.example.dwtest.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dwtest.feature.home.ui.components.Content

@Composable
internal fun HomeScreen(
    onAppClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Content(
        modifier = modifier,
        state = state,
        getInstalledAppsInfo = viewModel::getInstalledAppsInfo,
        onAppClick = onAppClick,
    )
}
