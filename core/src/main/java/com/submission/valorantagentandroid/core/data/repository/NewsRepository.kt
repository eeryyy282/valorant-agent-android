package com.submission.valorantagentandroid.core.data.repository

import com.submission.valorantagentandroid.core.data.NetworkBoundResource
import com.submission.valorantagentandroid.core.data.Resource
import com.submission.valorantagentandroid.core.data.source.local.LocalDataSource
import com.submission.valorantagentandroid.core.data.source.remote.NewsRemoteDataSource
import com.submission.valorantagentandroid.core.data.source.remote.network.ApiResponse
import com.submission.valorantagentandroid.core.data.source.remote.response.news.ArticlesItemResponse
import com.submission.valorantagentandroid.core.domain.model.News
import com.submission.valorantagentandroid.core.domain.repository.INewsRepository
import com.submission.valorantagentandroid.core.utils.DataMapperNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: LocalDataSource,
) : INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        object :
            NetworkBoundResource<List<News>, List<ArticlesItemResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map {
                    DataMapperNews.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ArticlesItemResponse>>> =
                remoteDataSource.getAllNews()


            override suspend fun saveCallResult(data: List<ArticlesItemResponse>) {
                val newsList = DataMapperNews.mapResponseToEntities(data)
                localDataSource.insertNews(newsList)
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()

}