package com.estebanposada.simpleapp.data.remote.dto

import kotlinx.serialization.SerialName

data class BookDto(
    @SerialName("author_name")
    val authorNames: List<String>?,
    @SerialName("cover_edition_key")
    val coverKey: String?,
    @SerialName("cover_i")
    val coverAlternativeKey: Int? = null,
    @SerialName("first_publish_year")
    val publishYear: Int,
    val languages: List<String>?,
    @SerialName("ratings_average")
    val averageRating: Double,
    val key: String,
    val title: String
)