package com.submission.valorantagentandroid.core.data

import com.submission.valorantagentandroid.core.data.source.local.LocalDataSource
import com.submission.valorantagentandroid.core.data.source.remote.RemoteDataSource
import com.submission.valorantagentandroid.core.data.source.remote.network.ApiResponse
import com.submission.valorantagentandroid.core.data.source.remote.response.AgentResponse
import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.domain.repository.IAgentRepository
import com.submission.valorantagentandroid.core.utils.AppExecutors
import com.submission.valorantagentandroid.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAgentRepository {
    override fun getAllAgent(): Flow<Resource<List<Agent>>> =
        object : NetworkBoundResource<List<Agent>, List<AgentResponse>>() {
            override fun loadFromDB(): Flow<List<Agent>> {
                return localDataSource.getAllAgent().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Agent>?): Boolean = false

            override suspend fun createCall(): Flow<ApiResponse<List<AgentResponse>>> =
                remoteDataSource.getAllAgent()

            override suspend fun saveCallResult(data: List<AgentResponse>) {
                val agentList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertAgent(agentList)
            }

        }.asFlow()

    override fun getFavoriteAgent(): Flow<List<Agent>> {
        return localDataSource.getFavoriteAgent().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteAgent(agent: Agent, state: Boolean) {
        val agentEntity = DataMapper.mapDomainToEntity(agent)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteAgent(agentEntity, state)
        }
    }
}
