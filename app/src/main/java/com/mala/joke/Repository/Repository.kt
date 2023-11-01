package com.mala.joke.Repository

import com.mala.joke.model.State
import com.mala.joke.model.domain.TheJoke
import com.mala.joke.network.Api
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository {
    suspend  fun getRepoJoke() : Flow<State<TheJoke?>> {
        return flow {
            val result = Api.myApiService.getJoke()
            if(State.Success(result.body()?.setup)!=null ||State.Success(result.body()?.delivery)!=null) {
                emit(State.Loading)
                delay(1000)
                try {

                    if (result.isSuccessful) {
                        emit(State.Success(result.body()))
                    } else {
                        emit(State.Error(result.message()))
                    }
                } catch (e: Exception) {
                    emit(State.Error(e.message.toString()))
                }
            }
        }
    }
}