package com.submission.valorantagentandroid.core.data.source.local.entity

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AgentEntityTest {

    @Test
    fun `test AgentEntity initialization`() {
        val agentEntity = AgentEntity(
            uuid = "agent-123",
            displayName = "Jett",
            description = "A fast and agile duelist.",
            developerName = "Riot Games",
            displayIcon = "https://example.com/icon.png",
            fullPortrait = "https://example.com/portrait.png",
            backgroundGradientColors = listOf("#FF5733", "#33FF57"),
            background = "https://example.com/background.png",
            isPlayableCharacter = true,
            isFavorite = false
        )
        assertEquals("agent-123", agentEntity.uuid)
        assertEquals("Jett", agentEntity.displayName)
        assertEquals("A fast and agile duelist.", agentEntity.description)
        assertEquals("Riot Games", agentEntity.developerName)
        assertEquals("https://example.com/icon.png", agentEntity.displayIcon)
        assertEquals("https://example.com/portrait.png", agentEntity.fullPortrait)
        assertEquals(listOf("#FF5733", "#33FF57"), agentEntity.backgroundGradientColors)
        assertEquals("https://example.com/background.png", agentEntity.background)
        assertTrue(agentEntity.isPlayableCharacter!!)
        assertFalse(agentEntity.isFavorite)
    }

    @Test
    fun `test modifying AgentEntity favorite status`() {
        val agentEntity = AgentEntity(
            uuid = "agent-123",
            displayName = "Jett",
            description = "A fast and agile duelist.",
            developerName = "Riot Games",
            displayIcon = "https://example.com/icon.png",
            fullPortrait = "https://example.com/portrait.png",
            backgroundGradientColors = listOf("#FF5733", "#33FF57"),
            background = "https://example.com/background.png",
            isPlayableCharacter = true,
            isFavorite = false
        )

        agentEntity.isFavorite = true
        assertTrue(agentEntity.isFavorite)
    }
}
