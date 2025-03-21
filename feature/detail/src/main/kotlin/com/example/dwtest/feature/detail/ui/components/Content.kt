package com.example.dwtest.feature.detail.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.example.dwtest.core.ui.component.ContentError
import com.example.dwtest.core.ui.component.ContentLoading
import com.example.dwtest.core.ui.theme.DWTestTheme
import com.example.dwtest.feature.detail.ui.UiState

@Composable
internal fun Content(
    modifier: Modifier = Modifier,
    state: UiState,
    selectedPackageName: String?,
    getInstalledAppsInfo: (String?) -> Unit,
    onGoToAppClicked: (String) -> Unit,
) {
    LifecycleResumeEffect(selectedPackageName) {
        getInstalledAppsInfo(selectedPackageName)

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
                    appInfo = state.appInfo,
                    onGoToAppClicked = onGoToAppClicked,
                )
            }

            is UiState.Error -> {
                ContentError(
                    message = state.message,
                    onRetryClicked = {
                        getInstalledAppsInfo(selectedPackageName)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() = DWTestTheme {
    Content(
        state = UiState.Success(),
        selectedPackageName = null,
        getInstalledAppsInfo = {},
        onGoToAppClicked = {}
    )
}
