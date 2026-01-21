package com.estebanposada.simpleapp.presentation.book_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.domain.usecase.get_book_detail.GetBookDetailUseCase
import com.estebanposada.domain.util.Constants.BOOK_ID
import com.estebanposada.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(BookDetailState())
    val state: State<BookDetailState> = _state


    init {
        savedStateHandle.get<String>(BOOK_ID)?.let { id ->
            getBook(id)
        }
    }

    private fun getBook(bookId: String) {
        viewModelScope.launch {
            when (val result = getBookDetailUseCase(bookId)) {
                is Resource.Success -> _state.value = BookDetailState(book = result.data)
                is Resource.Error -> _state.value = BookDetailState(error = result.error?.message)
            }
        }
    }
}