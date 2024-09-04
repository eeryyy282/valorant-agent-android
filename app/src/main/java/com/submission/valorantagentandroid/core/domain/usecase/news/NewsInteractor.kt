package com.submission.valorantagentandroid.core.domain.usecase.news

import com.submission.valorantagentandroid.core.domain.repository.INewsRepository

class NewsInteractor (private val newsRepository: INewsRepository): NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()
}