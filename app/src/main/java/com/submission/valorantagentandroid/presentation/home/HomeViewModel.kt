package com.submission.valorantagentandroid.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase
import com.submission.valorantagentandroid.core.domain.usecase.news.NewsUseCase

class HomeViewModel(agentUseCase: AgentUseCase, newsUseCase: NewsUseCase) : ViewModel() {
    val agent = agentUseCase.getAllAgent().asLiveData()
    val news = newsUseCase.getAllNews().asLiveData()
}
