package com.estebanposada.domain.repository

import com.estebanposada.domain.util.Resource
import com.estebanposada.domain.model.Book

interface BookRepository {
    suspend fun getBooks(q: String): Resource<List<Book>>
    suspend fun getBookById(id: String): Resource<Book>
}