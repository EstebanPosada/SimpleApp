package com.estebanposada.domain.usecase.get_books

import com.estebanposada.domain.repository.BookRepository

class GetBooksUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(q: String) = repository.getBooks(q)
}