package com.submission.valorantagentandroid.core.data.source.remote.response.agent

import com.google.gson.annotations.SerializedName

data class RoleResponse(

    @field:SerializedName("displayIcon")
    val displayIcon: String? = null,

    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("assetPath")
    val assetPath: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("uuid")
    val uuid: String? = null
)
