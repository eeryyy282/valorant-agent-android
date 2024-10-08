package com.submission.valorantagentandroid.core.data.source.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Setting")

class SettingPreference(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = booleanPreferencesKey("theme_setting")
    private val usernameKey = stringPreferencesKey("username")
    private val userBioKey = stringPreferencesKey("user_bio")
    private val userImageKey = stringPreferencesKey("user_image")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map {
            it[themeKey] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit {
            it[themeKey] = isDarkModeActive
        }
    }

    fun getUsername(): Flow<String> {
        return dataStore.data.map {
            it[usernameKey] ?: ""
        }
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit {
            it[usernameKey] = username
        }
    }

    fun getUserBio(): Flow<String> {
        return dataStore.data.map {
            it[userBioKey] ?: "My bio"
        }
    }

    suspend fun saveUserBio(userBio: String) {
        dataStore.edit {
            it[userBioKey] = userBio
        }
    }

    fun getUserImage(): Flow<String> {
        return dataStore.data.map {
            it[userImageKey] ?: ""
        }
    }

    suspend fun saveUserImage(userImage: String) {
        dataStore.edit {
            it[userImageKey] = userImage
        }
    }
}
