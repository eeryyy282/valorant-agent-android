package com.submission.valorantagentandroid.core.data.source.remote.network

import com.submission.valorantagentandroid.BuildConfig
import com.submission.valorantagentandroid.core.data.source.remote.response.news.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "valorant",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY_NEWS_API
    ): NewsResponse
}