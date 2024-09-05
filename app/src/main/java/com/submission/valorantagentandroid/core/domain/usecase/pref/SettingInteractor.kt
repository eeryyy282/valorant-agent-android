package com.submission.valorantagentandroid.core.domain.usecase.pref

import com.submission.valorantagentandroid.core.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow

class SettingInteractor (private val settingRepository: ISettingRepository) : SettingUseCase {
    override fun getThemeSetting(): Flow<Boolean> =
        settingRepository.getThemeSetting()


    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        settingRepository.saveThemeSetting(isDarkModeActive)


}