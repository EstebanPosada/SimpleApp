package com.estebanposada.simpleapp.presentation.book_list

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.simpleapp.common.Constants.EMPTY
import com.estebanposada.simpleapp.domain.usecase.get_books.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {
    private val _state = mutableStateOf(BookListState())
    val state: State<BookListState> = _state

    fun onQueryChange(q: String) {
        _state.value = _state.value.copy(query = q)
    }

    fun onSearch() {
        val query = _state.value.query
        if (query.isBlank()) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = getBooksUseCase(query)
                _state.value = _state.value.copy(
                    books = result ?: emptyList(),
                    isLoading = false,
                    error = EMPTY
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message.orEmpty())
            }
        }
    }
}