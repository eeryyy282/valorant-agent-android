package com.submission.valorantagentandroid.core.domain.repository

import com.submission.valorantagentandroid.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews(): Flow<com.submission.valorantagentandroid.core.data.Resource<List<News>>>
}