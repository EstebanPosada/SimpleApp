package com.estebanposada.simpleapp.data.remote.mapper

import com.estebanposada.simpleapp.data.remote.dto.BookDto
import com.estebanposada.simpleapp.data.remote.dto.test.BookDetailDto
import com.estebanposada.simpleapp.domain.model.Book
import com.estebanposada.simpleapp.domain.model.BookDetail

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
    rating = averageRating ?: 0.0
)

fun BookDetailDto.toBookDetail(): BookDetail = BookDetail(
    id = key.substringAfterLast("/"),
    last_modified = last_modified,
    latest_revision = latest_revision,
    links = links,
    revision = revision,
    subject_people = subject_people,
    subject_places = subject_places,
    title = title,
    rating = 4.5,
    languages = subject_people ?: emptyList(),
    imageUrl = title,
)