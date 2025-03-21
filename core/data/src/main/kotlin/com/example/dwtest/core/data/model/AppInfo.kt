package com.example.dwtest.core.data.model

import android.graphics.Bitmap

data class AppInfo(
    val icon: Bitmap? = null,
    val name: String = "",
    val version: String = "",
    val packageName: String = "",
)
