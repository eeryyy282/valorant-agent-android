package com.submission.valorantagentandroid.core.data.source.remote.response.agent

import com.google.gson.annotations.SerializedName

data class ListAgentResponse(
    @field:SerializedName("data")
    val data: List<AgentResponse>,

    @field:SerializedName("status")
    val status: Int
)



