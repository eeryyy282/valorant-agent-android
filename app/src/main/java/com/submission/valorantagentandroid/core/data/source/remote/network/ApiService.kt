package com.submission.valorantagentandroid.core.data.source.remote.network

import com.submission.valorantagentandroid.core.data.source.remote.response.ListAgentResponse
import retrofit2.http.GET

interface ApiService {
    @GET("agents")
    suspend fun getListAgent(): ListAgentResponse
}