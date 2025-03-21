package com.example.dwtest.feature.detail.ui

import android.graphics.Bitmap

sealed class UiState {
    data object Loading : UiState()

    data class Success(
        val appInfo: AppInfo = AppInfo(),
    ) : UiState()

    data class Error(
        val message: String = "",
    ) : UiState()
}

data class AppInfo(
    val icon: Bitmap? = null,
    val name: String = "",
    val version: String = "",
    val packageName: String = "",
    val checksum: String = "",
)
