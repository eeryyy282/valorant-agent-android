package com.submission.valorantagentandroid.core.data.source.remote.network

import com.submission.valorantagentandroid.BuildConfig
import com.submission.valorantagentandroid.core.data.source.remote.response.agent.ListAgentResponse
import com.submission.valorantagentandroid.core.data.source.remote.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AgentApiService {
    @GET("agents")
    suspend fun getListAgent(): ListAgentResponse



}