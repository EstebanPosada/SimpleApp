package com.estebanposada.simpleapp.presentation.book_list

import com.estebanposada.simpleapp.domain.model.Book

data class BookListState(
    val isLoading: Boolean = false,
    val query: String = "",
    val books: List<Book> = emptyList(),
    val error: String? = null
)