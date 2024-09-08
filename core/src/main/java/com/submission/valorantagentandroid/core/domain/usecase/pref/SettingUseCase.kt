package com.submission.valorantagentandroid.core.domain.usecase.pref

import kotlinx.coroutines.flow.Flow

interface SettingUseCase {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)

    fun getUsername(): Flow<String>
    suspend fun saveUsername(username: String)

    fun getUserBio(): Flow<String>
    suspend fun saveUserBio(userBio: String)

    fun getUserImage(): Flow<String>
    suspend fun saveUserImage(userImage: String)
}
