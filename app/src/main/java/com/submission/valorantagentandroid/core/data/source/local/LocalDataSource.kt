package com.submission.valorantagentandroid.core.data.source.local

import com.submission.valorantagentandroid.core.data.source.local.entity.AgentEntity
import com.submission.valorantagentandroid.core.data.source.local.entity.NewsEntity
import com.submission.valorantagentandroid.core.data.source.local.room.agent.AgentDao
import com.submission.valorantagentandroid.core.data.source.local.room.news.NewsDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val agentDao: AgentDao, private val newsDao: NewsDao) {

    fun getAllAgent(): Flow<List<AgentEntity>> = agentDao.getAllAgent()

    fun getFavoriteAgent(): Flow<List<AgentEntity>> = agentDao.getFavoriteAgent()

    fun getRandomAgent(): Flow<List<AgentEntity>> = agentDao.getRandomAgent()

    suspend fun insertAgent(agentList: List<AgentEntity>) = agentDao.insertAgent(agentList)

    fun setFavoriteAgent(agent: AgentEntity, newState: Boolean) {
        agent.isFavorite = newState
        agentDao.updateFavoriteAgent(agent)

    }

    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)
}