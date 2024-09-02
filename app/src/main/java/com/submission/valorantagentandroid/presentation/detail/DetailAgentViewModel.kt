package com.submission.valorantagentandroid.presentation.detail

import androidx.lifecycle.ViewModel
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.domain.usecase.AgentUseCase

class DetailAgentViewModel(private val agentUseCase: AgentUseCase) : ViewModel() {
    fun setFavoriteAgent(agent: Agent, newStatus: Boolean) =
        agentUseCase.setFavoriteAgent(agent, newStatus)

}