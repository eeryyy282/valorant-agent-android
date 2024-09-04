package com.submission.valorantagentandroid.core.data.source.remote.response.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItemResponse>,

    @field:SerializedName("status")
    val status: String? = null
)


