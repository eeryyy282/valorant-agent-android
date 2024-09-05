package com.submission.valorantagentandroid.core.domain.usecase.news

import com.submission.valorantagentandroid.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<com.submission.valorantagentandroid.core.data.Resource<List<News>>>
}