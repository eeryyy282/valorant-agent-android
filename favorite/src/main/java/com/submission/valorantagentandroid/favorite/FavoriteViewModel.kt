package com.submission.valorantagentandroid.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase

class FavoriteViewModel(agentUseCase: AgentUseCase) : ViewModel() {
    val favoriteAgent = agentUseCase.getFavoriteAgent().asLiveData()
}