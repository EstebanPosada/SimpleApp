package com.estebanposada.domain.mapper

interface ResultMapper<T, R> {
    fun map(input: T): R
}