package com.estebanposada.simpleapp.domain.repository

import com.estebanposada.simpleapp.domain.util.Resource
import com.estebanposada.simpleapp.domain.model.Book

interface BookRepository {
    suspend fun getBooks(q: String): Resource<List<Book>>
    suspend fun getBookById(id: String): Resource<Book>
}