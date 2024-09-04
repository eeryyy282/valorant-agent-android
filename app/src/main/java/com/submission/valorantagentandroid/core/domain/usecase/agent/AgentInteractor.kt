package com.submission.valorantagentandroid.core.domain.usecase.agent

import com.submission.valorantagentandroid.core.domain.model.Agent
import com.submission.valorantagentandroid.core.domain.repository.IAgentRepository
import kotlinx.coroutines.flow.Flow

class AgentInteractor(private val agentRepository: IAgentRepository) : AgentUseCase {
    override fun getAllAgent() =
        agentRepository.getAllAgent()


    override fun getFavoriteAgent() =
        agentRepository.getFavoriteAgent()

    override fun getOneAgent(): Flow<List<Agent>> =
        agentRepository.getRandomAgent()


    override fun setFavoriteAgent(agent: Agent, state: Boolean) =
        agentRepository.setFavoriteAgent(agent, state)


}