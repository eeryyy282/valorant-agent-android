package com.submission.valorantagentandroid.core.data.source.remote.response.agent

import com.google.gson.annotations.SerializedName

data class AgentResponse(
    @field:SerializedName("killfeedPortrait")
    val killfeedPortrait: String? = null,

    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("backgroundGradientColors")
    val backgroundGradientColors: List<String?>? = null,

    @field:SerializedName("uuid")
    val uuid: String,

    @field:SerializedName("fullPortrait")
    val fullPortrait: String? = null,

    @field:SerializedName("displayIcon")
    val displayIcon: String? = null,

    @field:SerializedName("background")
    val background: String? = null,

    @field:SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean? = null,

    @field:SerializedName("developerName")
    val developerName: String? = null
)