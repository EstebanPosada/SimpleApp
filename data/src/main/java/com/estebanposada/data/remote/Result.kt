package com.estebanposada.data.remote

sealed class Result<out D, out E: Errors> {
    data class Success<out D>(val data: D) : Result<D, Nothing>()
    data class Failure<out E : Errors>(val error: E) : Result<Nothing, E>()
}

interface Errors