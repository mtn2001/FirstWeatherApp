package com.example.myapplication.data.remote.model

import com.example.myapplication.data.remote.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(private val newsApi: NewsApi) {
    suspend fun getNews(): List<NewsModel>{
        return withContext(Dispatchers.IO){
             val news = newsApi.getNews()
            news.body() ?: emptyList()
        }
    }
}