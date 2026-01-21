package com.estebanposada.domain.util

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val error: ErrorType? = null, val cause: Throwable? = null) :
        Resource<Nothing>()
}

enum class ErrorType(val message: String) {
    NETWORK(Constants.NETWORK),
    HTTP_ERROR(Constants.HTTP_ERROR),
    UNKNOWN(Constants.UNKNOWN)
}