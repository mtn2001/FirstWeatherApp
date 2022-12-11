package com.example.myapplication.domain

import com.example.myapplication.domain.item.NewsItem
import com.example.myapplication.repo.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): List<NewsItem>{
        return newsRepository.getNews().shuffled()
    }
}