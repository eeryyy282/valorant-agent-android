package com.submission.valorantagentandroid.presentation.agent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase

class AgentViewModel(private val agentUseCase: AgentUseCase) : ViewModel() {
    val agentRandom = agentUseCase.getAllAgent().asLiveData()
    val agent = agentUseCase.getAllAgent().asLiveData()

    fun searchAgent(query: String) = agentUseCase.searchAgent(query).asLiveData()
}
