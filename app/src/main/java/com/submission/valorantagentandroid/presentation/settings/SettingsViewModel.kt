package com.submission.valorantagentandroid.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.submission.valorantagentandroid.core.domain.usecase.pref.SettingUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingUseCase: SettingUseCase) : ViewModel() {
    val getThemeSetting = settingUseCase.getThemeSetting().asLiveData()

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingUseCase.saveThemeSetting(isDarkModeActive)
        }
    }
}
