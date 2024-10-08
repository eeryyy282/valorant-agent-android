package com.submission.valorantagentandroid.core.data.source.remote

import android.util.Log
import com.submission.valorantagentandroid.core.data.source.remote.network.AgentApiService
import com.submission.valorantagentandroid.core.data.source.remote.network.ApiResponse
import com.submission.valorantagentandroid.core.data.source.remote.response.agent.AgentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AgentRemoteDataSource(private val apiService: AgentApiService) {
    suspend fun getAllAgent(): Flow<ApiResponse<List<AgentResponse>>> {
        return flow {
            try {
                val response = apiService.getListAgent()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSourceAgent", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
