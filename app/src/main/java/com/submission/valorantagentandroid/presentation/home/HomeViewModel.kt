package com.submission.valorantagentandroid.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.valorantagentandroid.core.domain.usecase.AgentUseCase

class HomeViewModel(agentUseCase: AgentUseCase) : ViewModel() {
    val agent = agentUseCase.getAllAgent().asLiveData()
}