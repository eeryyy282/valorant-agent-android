package com.submission.valorantagentandroid.core.data.source.remote.network

import com.submission.valorantagentandroid.core.data.source.remote.response.agent.ListAgentResponse
import retrofit2.http.GET

interface AgentApiService {
    @GET("agents")
    suspend fun getListAgent(): ListAgentResponse


}