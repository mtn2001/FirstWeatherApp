package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.model.NewsModel
import com.example.myapplication.util.Constants.Companion.GAMES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET(GAMES_ENDPOINT)
    suspend fun getNews(): Response<List<NewsModel>>
}