package com.estebanposada.domain.usecase.get_book_detail

import com.estebanposada.domain.repository.BookRepository

class GetBookDetailUseCase(private val repository: BookRepository) {
    suspend operator fun invoke(id: String) = repository.getBookById(id)
}