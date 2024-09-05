package com.submission.valorantagentandroid.core.utils

import com.submission.valorantagentandroid.core.data.source.local.entity.AgentEntity
import com.submission.valorantagentandroid.core.data.source.remote.response.agent.AgentResponse
import com.submission.valorantagentandroid.core.domain.model.Agent

object DataMapperAgent {
    fun mapResponseToEntities(input: List<AgentResponse>): List<AgentEntity> {
        val agentList = ArrayList<AgentEntity>()
        input.map {
            val agent = AgentEntity(
                background = it.background,
                description = it.description,
                displayIcon = it.displayIcon,
                displayName = it.displayName,
                fullPortrait = it.fullPortrait,
                isFavorite = false,
                uuid = it.uuid,
                developerName = it.developerName,
                isPlayableCharacter = it.isPlayableCharacter,
                backgroundGradientColors = it.backgroundGradientColors
            )
            agentList.add(agent)
        }
        return agentList
    }

    fun mapEntitiesToDomain(input: List<AgentEntity>): List<Agent> =
        input.map {
            Agent(
                background = it.background,
                description = it.description,
                displayIcon = it.displayIcon,
                displayName = it.displayName,
                uuid = it.uuid,
                fullPortrait = it.fullPortrait,
                developerName = it.developerName,
                backgroundGradientColors = it.backgroundGradientColors,
                isPlayableCharacter = it.isPlayableCharacter,
                isFavorite = it.isFavorite,

                )
        }

    fun mapDomainToEntity(input: Agent) =
        AgentEntity(
            background = input.background,
            description = input.description,
            displayIcon = input.displayIcon,
            displayName = input.displayName,
            uuid = input.uuid,
            fullPortrait = input.fullPortrait,
            developerName = input.developerName,
            backgroundGradientColors = input.backgroundGradientColors,
            isPlayableCharacter = input.isPlayableCharacter,
            isFavorite = input.isFavorite,
        )

}