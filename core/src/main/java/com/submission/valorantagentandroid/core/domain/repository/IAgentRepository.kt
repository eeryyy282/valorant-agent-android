package com.submission.valorantagentandroid.core.domain.repository

import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.domain.model.Agent
import kotlinx.coroutines.flow.Flow

interface IAgentRepository {
    fun getAllAgent(): Flow<Resource<List<Agent>>>

    fun getFavoriteAgent(): Flow<List<Agent>>

    fun getRandomAgent(): Flow<List<Agent>>

    fun setFavoriteAgent(agent: Agent, state: Boolean)

    fun searchAgent(query: String): Flow<List<Agent>>
}