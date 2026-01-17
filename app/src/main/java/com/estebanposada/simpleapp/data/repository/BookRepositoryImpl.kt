package com.estebanposada.simpleapp.data.repository

import com.estebanposada.simpleapp.data.remote.BookApi
import com.estebanposada.simpleapp.data.remote.dto.BookDto
import com.estebanposada.simpleapp.data.remote.dto.test.BookDetailDto
import com.estebanposada.simpleapp.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
) : BookRepository {
    override suspend fun getBooks(q: String): List<BookDto> = api.searchBook(q).docs

    override suspend fun getBookById(id: String): BookDetailDto = api.getDescription(id)
}