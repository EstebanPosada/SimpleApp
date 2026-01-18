package com.estebanposada.simpleapp.domain.repository

import com.estebanposada.simpleapp.common.Resource
import com.estebanposada.simpleapp.domain.model.Book
import com.estebanposada.simpleapp.domain.model.BookDetail

interface BookRepository {
    suspend fun getBooks(q: String): Resource<List<Book>>
    suspend fun getBookById(id: String): Resource<BookDetail>
}