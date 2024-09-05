package com.submission.valorantagentandroid.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase
import com.submission.valorantagentandroid.core.domain.usecase.pref.SettingUseCase

class DetailAgentViewModel(
    private val agentUseCase: AgentUseCase,
    settingUseCase: SettingUseCase
) : ViewModel() {
    val getThemeSetting = settingUseCase.getThemeSetting().asLiveData()

    fun setFavoriteAgent(agent: Agent, newStatus: Boolean) =
        agentUseCase.setFavoriteAgent(agent, newStatus)

}