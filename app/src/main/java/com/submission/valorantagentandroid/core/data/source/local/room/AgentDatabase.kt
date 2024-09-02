package com.submission.valorantagentandroid.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.submission.valorantagentandroid.core.data.source.local.entity.AgentEntity
import com.submission.valorantagentandroid.core.utils.ConverterBackgroundGradient

@Database(entities = [AgentEntity::class], version = 1, exportSchema = false)
@TypeConverters(ConverterBackgroundGradient::class)
abstract class AgentDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
}