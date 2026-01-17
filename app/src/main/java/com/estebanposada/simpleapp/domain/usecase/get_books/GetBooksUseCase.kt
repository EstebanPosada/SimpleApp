package com.estebanposada.simpleapp.domain.usecase.get_books

import com.estebanposada.simpleapp.data.remote.mapper.toBook
import com.estebanposada.simpleapp.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(q: String) = repository.getBooks(q).map { it.toBook() }
}