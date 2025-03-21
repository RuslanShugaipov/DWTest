package com.example.dwtest.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dwtest.core.common.entity.Entity
import com.example.dwtest.core.data.repository.InstalledAppsInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: InstalledAppsInfoRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getInstalledAppsInfo() {
        viewModelScope.launch {
            _uiState.emit(UiState.Loading)

            when (val result = repository.getInstalledAppsInfo()) {
                is Entity.Success -> {
                    val appsInfo = result.data.map { appInfo ->
                        AppInfo(
                            name = appInfo.name,
                            icon = appInfo.icon,
                            packageName = appInfo.packageName,
                        )
                    }
                    _uiState.emit(UiState.Success(appsInfo = appsInfo))
                }

                is Entity.Error -> {
                    _uiState.emit(UiState.Error(message = result.exception.message.toString()))
                }
            }
        }
    }
}
