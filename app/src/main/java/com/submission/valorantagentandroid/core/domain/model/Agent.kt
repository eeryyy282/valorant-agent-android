package com.submission.valorantagentandroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val uuid: String,
    val displayName: String?,
    val description: String?,
    val developerName: String?,
    val displayIcon: String?,
    val fullPortrait: String?,
    val background: String?,
    val backgroundGradientColors: List<String?>?,
    val isFavorite: Boolean
) : Parcelable