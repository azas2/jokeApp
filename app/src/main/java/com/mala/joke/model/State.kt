package com.mala.joke.model


sealed class State<out T>{
    object Loading:State<Nothing>()
    data class Success<T>(val data:T):State<T>()
    data class Error<T>(val message:String):State<T>()
}
