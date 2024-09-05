package com.submission.valorantagentandroid.core.utils

import com.submission.valorantagentandroid.core.data.source.local.entity.NewsEntity
import com.submission.valorantagentandroid.core.data.source.remote.response.news.ArticlesItemResponse
import com.submission.valorantagentandroid.core.domain.model.News

object DataMapperNews {
    fun mapResponseToEntities(input: List<ArticlesItemResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                description = it.description,
                author = it.author,
                publishedAt = it.publishedAt
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                description = it.description,
                author = it.author,
                publishedAt = it.publishedAt
            )
        }
}