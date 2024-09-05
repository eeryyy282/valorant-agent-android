package com.submission.valorantagentandroid.core.data.repository

import com.submission.valorantagentandroid.core.data.source.local.LocalDataSource
import com.submission.valorantagentandroid.core.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow

class SettingRepository(
    private val localDataSource: LocalDataSource
) : ISettingRepository {
    override fun getThemeSetting(): Flow<Boolean> {
        return localDataSource.getThemeSetting()
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        return localDataSource.saveThemeSetting(isDarkModeActive)
    }

}