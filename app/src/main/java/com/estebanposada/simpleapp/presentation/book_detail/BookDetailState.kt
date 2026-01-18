package com.estebanposada.simpleapp.presentation.book_detail

import com.estebanposada.simpleapp.domain.model.BookDetail

data class BookDetailState(
    val isLoading: Boolean = false,
    val book: BookDetail? = null,
    val error: String? = null
)
