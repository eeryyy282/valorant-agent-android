package com.submission.valorantagentandroid.core.data.source.remote

import android.util.Log
import com.submission.valorantagentandroid.core.data.source.remote.network.ApiResponse
import com.submission.valorantagentandroid.core.data.source.remote.network.NewsApiService
import com.submission.valorantagentandroid.core.data.source.remote.response.news.ArticlesItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRemoteDataSource(private val apiService: NewsApiService) {
    suspend fun getAllNews(): Flow<ApiResponse<List<ArticlesItemResponse>>> {
        return flow {
            try {
                val response = apiService.getNews()
                val dataArray = response.articles
                if (dataArray.isNotEmpty()) {
                    val news = dataArray.take(5)
                    emit(ApiResponse.Success(news))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSourceNews", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
