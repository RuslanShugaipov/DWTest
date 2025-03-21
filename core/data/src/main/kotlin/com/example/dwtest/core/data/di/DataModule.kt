package com.example.dwtest.core.data.di

import android.content.Context
import com.example.dwtest.core.data.repository.InstalledAppsInfoRepository
import com.example.dwtest.core.data.repository.InstalledAppsInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    internal fun provideInstalledAppsInfoRepository(
        @ApplicationContext appContext: Context,
    ): InstalledAppsInfoRepository {
        return InstalledAppsInfoRepositoryImpl(
            packageManager = appContext.packageManager,
            appPackageName = appContext.packageName,
        )
    }
}
