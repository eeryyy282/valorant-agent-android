package com.submission.valorantagentandroid.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.submission.valorantagentandroid.core.data.source.local.entity.AgentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentDao {
    @Query("SELECT * FROM agent WHERE isPlayableCharacter = 1")
    fun getAllAgent(): Flow<List<AgentEntity>>

    @Query("SELECT * FROM agent where isFavorite = 1")
    fun getFavoriteAgent(): Flow<List<AgentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgent(agent: List<AgentEntity>)

    @Update
    fun updateFavoriteAgent(agent: AgentEntity)

}