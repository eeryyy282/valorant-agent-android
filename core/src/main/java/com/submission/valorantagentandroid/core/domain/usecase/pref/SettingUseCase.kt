package com.submission.valorantagentandroid.core.domain.usecase.pref

import kotlinx.coroutines.flow.Flow

interface SettingUseCase {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}
