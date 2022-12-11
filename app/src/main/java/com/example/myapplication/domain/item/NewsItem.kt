package com.example.myapplication.domain.item

import com.example.myapplication.data.remote.model.NewsModel

data class NewsItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
)
fun NewsModel.toNewsItem() = NewsItem(id, title, thumbnail, short_description)