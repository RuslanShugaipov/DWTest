package com.example.dwtest.feature.home.ui

import android.graphics.Bitmap

sealed class UiState {
    data object Loading : UiState()

    data class Success(
        val appsInfo: List<AppInfo> = emptyList(),
    ) : UiState()

    data class Error(
        val message: String = "",
    ) : UiState()
}

data class AppInfo(
    val name: String = "",
    val icon: Bitmap? = null,
    val packageName: String = "",
)
