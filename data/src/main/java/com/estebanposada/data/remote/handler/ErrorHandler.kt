package com.estebanposada.data.remote.handler

import com.estebanposada.data.remote.Result
import okio.IOException
import retrofit2.HttpException

/*
fun Throwable.toResult(): Result<Failure, Nothing> {
    return when (this) {
        is IOException -> Result.Failure
        is HttpException -> {
            val code = code()
            val message = message()
            Result.Failure(Failure.ServerError(code, message))
        }

        else -> Result.Failure(UnknownError(this))
    }
}*/
