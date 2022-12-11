package com.example.myapplication.repo

import com.example.myapplication.data.remote.model.NewsService
import com.example.myapplication.domain.item.NewsItem
import com.example.myapplication.domain.item.toNewsItem
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun getNews(): List<NewsItem>{
        return newsService.getNews().map {
            it.toNewsItem()
        }
    }
}