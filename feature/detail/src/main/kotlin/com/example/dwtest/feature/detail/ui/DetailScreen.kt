package com.example.dwtest.feature.detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dwtest.feature.detail.ui.components.Content

@Composable
internal fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedPackageName by viewModel.selectedPackageName.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val packageManager = context.packageManager

    Content(
        modifier = modifier,
        state = state,
        selectedPackageName = selectedPackageName,
        getInstalledAppsInfo = { packageName ->
            viewModel.getInstalledAppInfo(packageName)
        },
        onGoToAppClicked = { packageName ->
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            intent?.let { context.startActivity(it) }
        }
    )
}
