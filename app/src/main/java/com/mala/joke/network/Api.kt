package com.mala.joke.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    val baseUrl="https://v2.jokeapi.dev"
    val retrofit=Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val myApiService= retrofit.create(ApiService::class.java)
}