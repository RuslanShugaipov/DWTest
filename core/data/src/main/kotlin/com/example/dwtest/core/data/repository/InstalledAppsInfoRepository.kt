package com.example.dwtest.core.data.repository

import com.example.dwtest.core.common.entity.Entity
import com.example.dwtest.core.data.model.AppInfo

interface InstalledAppsInfoRepository {

    suspend fun getInstalledAppsInfo(): Entity<List<AppInfo>>

    suspend fun calculateApkChecksumByPackageName(packageName: String): Entity<String>
}
