package com.submission.valorantagentandroid.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "author")
    var author: String?,

    @ColumnInfo("title")
    var title: String?,

    @ColumnInfo("description")
    var description: String?,

    @PrimaryKey
    @ColumnInfo("url")
    var url: String,

    @ColumnInfo("urlToImage")
    var urlToImage: String?,

    @ColumnInfo("publishedAt")
    var publishedAt: String?,
)
