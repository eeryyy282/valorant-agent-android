package com.submission.valorantagentandroid.core.data.source.local.entity

import org.junit.Assert.assertEquals
import org.junit.Test

class NewsEntityTest {

    @Test
    fun `test NewsEntity initialization`() {
        val newsEntity = NewsEntity(
            author = "John Doe",
            title = "New Updates Released",
            description = "Details about the latest update",
            url = "https://example.com/news/1",
            urlToImage = "https://example.com/images/news1.png",
            publishedAt = "2024-09-08"
        )

        assertEquals("John Doe", newsEntity.author)
        assertEquals("New Updates Released", newsEntity.title)
        assertEquals("Details about the latest update", newsEntity.description)
        assertEquals("https://example.com/news/1", newsEntity.url)
        assertEquals("https://example.com/images/news1.png", newsEntity.urlToImage)
        assertEquals("2024-09-08", newsEntity.publishedAt)
    }

    @Test
    fun `test modifying NewsEntity fields`() {
        val newsEntity = NewsEntity(
            author = "John Doe",
            title = "New Updates Released",
            description = "Details about the latest update",
            url = "https://example.com/news/1",
            urlToImage = "https://example.com/images/news1.png",
            publishedAt = "2024-09-08"
        )

        newsEntity.title = "Updated Title"
        newsEntity.description = "Updated description about the news"
        newsEntity.author = "Jane Smith"

        assertEquals("Updated Title", newsEntity.title)
        assertEquals("Updated description about the news", newsEntity.description)
        assertEquals("Jane Smith", newsEntity.author)
    }
}
