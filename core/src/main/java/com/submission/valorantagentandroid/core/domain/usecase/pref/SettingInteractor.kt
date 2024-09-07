package com.submission.valorantagentandroid.core.domain.usecase.pref

import com.submission.valorantagentandroid.core.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow

class SettingInteractor(private val settingRepository: ISettingRepository) : SettingUseCase {
    override fun getThemeSetting(): Flow<Boolean> =
        settingRepository.getThemeSetting()

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) =
        settingRepository.saveThemeSetting(isDarkModeActive)

    override fun getUsername(): Flow<String> =
        settingRepository.getUserName()

    override suspend fun saveUsername(username: String) =
        settingRepository.saveUserName(username)

    override fun getUserBio(): Flow<String> =
        settingRepository.getUserBio()

    override suspend fun saveUserBio(userBio: String) =
        settingRepository.saveUserBio(userBio)

    override fun getUserImage(): Flow<String> =
        settingRepository.getUserImage()

    override suspend fun saveUserImage(userImage: String) =
        settingRepository.saveUserImage(userImage)
}
