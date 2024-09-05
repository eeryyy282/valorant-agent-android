package com.submission.valorantagentandroid.core.data

import com.submission.valorantagentandroid.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<com.submission.valorantagentandroid.core.data.Resource<ResultType>> =
        flow {
            emit(com.submission.valorantagentandroid.core.data.Resource.Loading())
            val dbSource = loadFromDB().first()
            if (shouldFetch(dbSource)) {
                emit(com.submission.valorantagentandroid.core.data.Resource.Loading())
                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDB().map {
                            com.submission.valorantagentandroid.core.data.Resource.Success(
                                it
                            )
                        })
                    }

                    is ApiResponse.Empty -> {
                        emitAll(loadFromDB().map {
                            com.submission.valorantagentandroid.core.data.Resource.Success(
                                it
                            )
                        })
                    }

                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(
                            com.submission.valorantagentandroid.core.data.Resource.Error(
                                apiResponse.errorMessage
                            )
                        )
                    }
                }
            } else {
                emitAll(loadFromDB().map {
                    com.submission.valorantagentandroid.core.data.Resource.Success(
                        it
                    )
                })
            }
        }

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<com.submission.valorantagentandroid.core.data.Resource<ResultType>> = result
}