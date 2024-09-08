package com.submission.valorantagentandroid.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase
import com.submission.valorantagentandroid.core.domain.usecase.pref.SettingUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingUseCase: SettingUseCase,
    agentUseCase: AgentUseCase
) : ViewModel() {
    val getThemeSetting = settingUseCase.getThemeSetting().asLiveData()
    val getUsername = settingUseCase.getUsername().asLiveData()
    val getUserBio = settingUseCase.getUserBio().asLiveData()
    val getUserImage = settingUseCase.getUserImage().asLiveData()
    val agent = agentUseCase.getOneAgent().asLiveData()

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingUseCase.saveThemeSetting(isDarkModeActive)
        }
    }

    fun saveUserImage(imageUri: String) {
        viewModelScope.launch {
            settingUseCase.saveUserImage(imageUri)
        }
    }
}
