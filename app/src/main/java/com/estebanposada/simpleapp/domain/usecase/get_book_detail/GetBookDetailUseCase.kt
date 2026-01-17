package com.estebanposada.simpleapp.domain.usecase.get_book_detail

import com.estebanposada.simpleapp.data.remote.mapper.toBookDetail
import com.estebanposada.simpleapp.domain.repository.BookRepository
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id: String) = repository.getBookById(id).toBookDetail()
}