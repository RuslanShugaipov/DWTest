package com.example.dwtest.feature.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.example.dwtest.core.ui.component.ContentError
import com.example.dwtest.core.ui.component.ContentLoading
import com.example.dwtest.feature.home.ui.UiState

@Composable
internal fun Content(
    modifier: Modifier = Modifier,
    state: UiState,
    getInstalledAppsInfo: () -> Unit,
    onAppClick: (String) -> Unit,
) {
    LifecycleResumeEffect(Unit) {
        getInstalledAppsInfo()

        onPauseOrDispose {}
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        when (state) {
            is UiState.Loading -> {
                ContentLoading()
            }

            is UiState.Success -> {
                ContentSuccess(
                    appsInfo = state.appsInfo,
                    onAppClick = onAppClick,
                )
            }

            is UiState.Error -> {
                ContentError(
                    message = state.message,
                    onRetryClicked = getInstalledAppsInfo
                )
            }
        }
    }
}
