package com.submission.valorantagentandroid.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISettingRepository {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)

    fun getUserName(): Flow<String>
    suspend fun saveUserName(userName: String)

    fun getUserBio(): Flow<String>
    suspend fun saveUserBio(userBio: String)

    fun getUserImage(): Flow<String>
    suspend fun saveUserImage(userImage: String)
}
