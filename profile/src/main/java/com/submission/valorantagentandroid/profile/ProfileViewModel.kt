package com.submission.valorantagentandroid.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase
import com.submission.valorantagentandroid.core.domain.usecase.pref.SettingUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val settingUseCase: SettingUseCase,
    agentUseCase: AgentUseCase
) : ViewModel() {
    val getTheme = settingUseCase.getThemeSetting().asLiveData()
    val getUsername = settingUseCase.getUsername().asLiveData()
    val getUserBio = settingUseCase.getUserBio().asLiveData()
    val getUserImage = settingUseCase.getUserImage().asLiveData()
    val agent = agentUseCase.getAllAgent().asLiveData()

    fun saveUsername(username: String) {
        viewModelScope.launch {
            settingUseCase.saveUsername(username)
        }
    }

    fun saveUserBio(userBio: String) {
        viewModelScope.launch {
            settingUseCase.saveUserBio(userBio)
        }
    }

    fun saveUserImage(userImage: String) {
        viewModelScope.launch {
            settingUseCase.saveUserImage(userImage)
        }
    }
}
