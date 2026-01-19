package com.estebanposada.simpleapp.data.remote.dto.test

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val author: AuthorX,
    val type: TypeXX
)