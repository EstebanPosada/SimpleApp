package com.estebanposada.simpleapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("author_name")
    val authorNames: List<String>? = null,
    @SerializedName("cover_edition_key")
    val coverKey: String? = null,
    @SerializedName("cover_i")
    val coverAlternativeKey: Int? = null,
    @SerializedName("first_publish_year")
    val publishYear: Int? = null,
    val language: List<String>? = null,
    @SerializedName("ratings_average")
    val averageRating: Double? = 0.0,
    val key: String,
    val title: String
)