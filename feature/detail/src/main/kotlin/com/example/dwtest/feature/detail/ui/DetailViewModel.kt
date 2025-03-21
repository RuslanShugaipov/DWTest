package com.example.dwtest.feature.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.dwtest.core.common.entity.Entity
import com.example.dwtest.core.data.repository.InstalledAppsInfoRepository
import com.example.dwtest.feature.detail.router.DetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PACKAGE_NAME_KEY = "selectedPackageName"

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: InstalledAppsInfoRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val route = savedStateHandle.toRoute<DetailRoute>()
    val selectedPackageName: StateFlow<String?> = savedStateHandle.getStateFlow(
        key = PACKAGE_NAME_KEY,
        initialValue = route.packageName,
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getInstalledAppInfo(selectedAppPackageName: String?) {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)

            selectedAppPackageName?.let { packageName ->
                when (val result = repository.getInstalledAppsInfo()) {
                    is Entity.Success -> {
                        val appInfo = result.data.firstOrNull { data ->
                            data.packageName == packageName
                        }

                        if (appInfo == null) {
                            _uiState.emit(UiState.Error())
                        } else {
                            val checksum = calculateApkChecksumByPackageName(packageName)
                            val detailAppInfo = AppInfo(
                                name = appInfo.name,
                                icon = appInfo.icon,
                                version = appInfo.version,
                                packageName = appInfo.packageName,
                                checksum = checksum.orEmpty()
                            )
                            _uiState.emit(UiState.Success(appInfo = detailAppInfo))
                        }
                    }

                    is Entity.Error -> {
                        _uiState.emit(UiState.Error(message = result.exception.message.toString()))
                    }
                }
            } ?: run {
                _uiState.emit(UiState.Error())
            }
        }
    }

    private suspend fun calculateApkChecksumByPackageName(packageName: String): String? =
        when (val result = repository.calculateApkChecksumByPackageName(packageName)) {
            is Entity.Success -> result.data
            is Entity.Error -> null
        }
}
