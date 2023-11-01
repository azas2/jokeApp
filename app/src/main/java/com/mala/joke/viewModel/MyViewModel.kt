package com.mala.joke.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mala.joke.Repository.Repository
import com.mala.joke.model.State

import kotlinx.coroutines.launch

class MyViewModel<TheJoke> : ViewModel() {
    val repo= Repository()
    private val _getJoke= MutableLiveData<State<com.mala.joke.model.domain.TheJoke?>>()
    val getJoke: LiveData<State<com.mala.joke.model.domain.TheJoke?>>
        get()=_getJoke
    fun getMyJokBro(){
        viewModelScope.launch {
            repo.getRepoJoke().collect{
                    _getJoke.postValue(it)
            }
        }
    }

    init {
        getMyJokBro()
    }

}


