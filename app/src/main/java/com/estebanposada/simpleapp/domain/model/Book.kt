package com.estebanposada.simpleapp.domain.model

data class Book(
    val id: String,
    val authors: List<String>,
    val imageUrl: String,
    val languages: List<String>?,
    val publishYear: String,
    val rating: Double?,
    val title: String,
    val description: String?
)
