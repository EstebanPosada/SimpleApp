package com.estebanposada.data.remote.mapper

import com.estebanposada.data.local.entity.BookEntity
import com.estebanposada.data.remote.dto.BookDto
import com.estebanposada.domain.model.Book

fun BookDto.toBook(): Book = Book(
    id = key.substringAfterLast("/"),
    title = title,
    imageUrl = if (coverKey != null) {
        "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
    } else {
        "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
    },
    authors = authorNames ?: emptyList(),
    languages = language ?: emptyList(),
    publishYear = publishYear.toString(),
    rating = averageRating ?: 0.0,
    description = description
)

fun BookDto.toBookEntity() = BookEntity(
    id = key.substringAfterLast("/"),
    authors = authorNames ?: emptyList(),
    imageUrl = if (coverKey != null) {
        "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
    } else {
        "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
    },
    languages = language ?: emptyList(),
    publishYear = publishYear.toString(),
    rating = averageRating ?: 0.0,
    title = title,
    description = description,
    links = links ?: emptyList(),
)

fun BookEntity.toBook() = Book(
    id = id,
    authors = authors,
    imageUrl = imageUrl,
    languages = languages,
    publishYear = publishYear,
    rating = rating,
    title = title,
    description = description
)
