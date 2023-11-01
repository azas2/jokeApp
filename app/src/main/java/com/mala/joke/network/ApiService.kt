package com.mala.joke.network

import com.mala.joke.model.domain.TheJoke
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("joke/Any")
    suspend fun getJoke():Response<TheJoke>
}