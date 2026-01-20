package com.estebanposada.simpleapp.presentation.book_detail

import com.estebanposada.simpleapp.domain.model.Book

data class BookDetailState(
    val isLoading: Boolean = false,
    val book: Book? = null,
    val error: String? = null
)
