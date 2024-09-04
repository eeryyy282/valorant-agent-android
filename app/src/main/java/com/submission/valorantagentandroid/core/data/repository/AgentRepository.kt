package com.submission.valorantagentandroid.core.data.repository

import com.submission.valorantagentandroid.core.data.NetworkBoundResource
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.data.source.local.LocalDataSource
import com.submission.valorantagentandroid.core.data.source.remote.AgentRemoteDataSource
import com.submission.valorantagentandroid.core.data.source.remote.network.ApiResponse
import com.submission.valorantagentandroid.core.data.source.remote.response.agent.AgentResponse
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.domain.repository.IAgentRepository
import com.submission.valorantagentandroid.core.utils.AppExecutors
import com.submission.valorantagentandroid.core.utils.DataMapperAgent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentRepository(
    private val agentRemoteDataSource: AgentRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAgentRepository {
    override fun getAllAgent(): Flow<Resource<List<Agent>>> =
        object : NetworkBoundResource<List<Agent>, List<AgentResponse>>() {
            override fun loadFromDB(): Flow<List<Agent>> {
                return localDataSource.getAllAgent().map {
                    DataMapperAgent.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Agent>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<AgentResponse>>> =
                agentRemoteDataSource.getAllAgent()

            override suspend fun saveCallResult(data: List<AgentResponse>) {
                val agentList = DataMapperAgent.mapResponseToEntities(data)
                localDataSource.insertAgent(agentList)
            }

        }.asFlow()

    override fun getFavoriteAgent(): Flow<List<Agent>> {
        return localDataSource.getFavoriteAgent().map {
            DataMapperAgent.mapEntitiesToDomain(it)
        }
    }

    override fun getRandomAgent(): Flow<List<Agent>> {
        return localDataSource.getRandomAgent().map {
            DataMapperAgent.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAgent(agent: Agent, state: Boolean) {
        val agentEntity = DataMapperAgent.mapDomainToEntity(agent)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteAgent(agentEntity, state)
        }
    }
}
