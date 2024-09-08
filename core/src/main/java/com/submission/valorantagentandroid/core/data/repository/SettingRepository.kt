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

    override fun getUserName(): Flow<String> {
        return localDataSource.getUsername()
    }

    override suspend fun saveUserName(userName: String) {
        return localDataSource.saveUsername(userName)
    }

    override fun getUserBio(): Flow<String> {
        return localDataSource.getUserBio()
    }

    override suspend fun saveUserBio(userBio: String) {
        return localDataSource.saveUserBio(userBio)
    }

    override fun getUserImage(): Flow<String> {
        return localDataSource.getUserImage()
    }

    override suspend fun saveUserImage(userImage: String) {
        return localDataSource.saveUserImage(userImage)
    }
}
