package com.estebanposada.simpleapp

import com.estebanposada.simpleapp.data.remote.dto.BookDto
import com.estebanposada.simpleapp.data.remote.dto.test.Author
import com.estebanposada.simpleapp.data.remote.dto.test.AuthorX
import com.estebanposada.simpleapp.data.remote.dto.BookDetailDto
import com.estebanposada.simpleapp.data.remote.dto.test.Created
import com.estebanposada.simpleapp.data.remote.dto.test.LastModified
import com.estebanposada.simpleapp.data.remote.dto.Link
import com.estebanposada.simpleapp.data.remote.dto.test.TypeXX

val bookDetailDto = BookDetailDto(
    authors = listOf(Author(author = AuthorX("x"), type = TypeXX("type"))),
    created = Created("type", "val"),
    key = "key",
    last_modified = LastModified("type", "value"),
    latest_revision = 123,
    description = "description",
    links = listOf(Link("title", TypeXX("type"), "url")),
    revision = 123,
    subject_people = listOf("subje"),
    subject_places = listOf("subject"),
    title = "title",
    type = TypeXX("type")
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