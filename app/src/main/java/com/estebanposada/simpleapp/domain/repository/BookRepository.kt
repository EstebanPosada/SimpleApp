package com.estebanposada.simpleapp.domain.repository

import com.estebanposada.simpleapp.data.remote.dto.BookDto
import com.estebanposada.simpleapp.data.remote.dto.test.BookDetailDto

interface BookRepository {
    suspend fun getBooks(q: String): List<BookDto>
    suspend fun getBookById(id: String): BookDetailDto
}