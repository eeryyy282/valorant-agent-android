package com.submission.valorantagentandroid.core.domain.usecase.agent

import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.domain.model.Agent
import kotlinx.coroutines.flow.Flow

interface AgentUseCase {
    fun getAllAgent(): Flow<Resource<List<Agent>>>
    fun getFavoriteAgent(): Flow<List<Agent>>
    fun getOneAgent(): Flow<List<Agent>>
    fun setFavoriteAgent(agent: Agent, state: Boolean)
}
