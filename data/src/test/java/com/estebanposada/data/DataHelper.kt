package com.estebanposada.data

import com.estebanposada.data.local.entity.BookEntity
import com.estebanposada.data.remote.dto.BookDetailDto
import com.estebanposada.data.remote.dto.BookDto
import com.estebanposada.data.remote.dto.Link

val bookDetailDto = BookDetailDto(
    description = "description",
    links = listOf(Link("title", "url")),
)

val bookDto = BookDto(
    authorNames = listOf("Author"),
    coverKey = "12",
    coverAlternativeKey = 456,
    publishYear = 123,
    language = listOf("lang"),
    averageRating = 2.3,
    key = "key",
    title = "title"
)

val bookEntity = BookEntity(
    id = "id",
    authors = emptyList(),
    imageUrl = "image",
    languages = emptyList(),
    publishYear = "123",
    rating = 2.0,
    title = "title",
    description = "desc",
    links = emptyList()
)