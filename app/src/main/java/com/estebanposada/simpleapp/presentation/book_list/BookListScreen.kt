package com.estebanposada.simpleapp.presentation.book_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.estebanposada.simpleapp.domain.model.Book
import com.estebanposada.simpleapp.presentation.book_list.components.BookListItem
import com.estebanposada.simpleapp.presentation.book_list.components.SearchBar

@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val state = viewModel.state.value
    BookList(state, viewModel::onQueryChange, onSearch = viewModel::onSearch, onItemClick)
}

@Composable
fun BookList(
    state: BookListState,
    onQuerySearch: (String) -> Unit,
    onSearch: () -> Unit,
    onItemCLick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            SearchBar(query = state.query, onQueryChange = onQuerySearch, onSearch = onSearch)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(state.books) { book ->
                    BookListItem(
                        book = book,
                        onItemClick = { onItemCLick(it) }
                    )
                }
            }
        }
        when {
            state.error != null -> {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookListPreview() {
    val book = Book(
        id = "id",
        authors = listOf("Author"),
        imageUrl = "http://test.com",
        languages = listOf("lang"),
        publishYear = "1959",
        rating = 4.5,
        title = "Title"
    )
    val books = mutableListOf<Book>()
    repeat(5, { books.add(book) })
    BookList(state = BookListState(books = books), onQuerySearch = {}, onSearch = {}) { }
}

@Preview(showBackground = true)
@Composable
private fun BookListPreviewLoading() {
    BookList(state = BookListState(isLoading = true), onQuerySearch = {}, onSearch = {}) { }
}

@Preview(showBackground = true)
@Composable
private fun BookListPreviewError() {
    BookList(state = BookListState(error = "Error"), onQuerySearch = {}, onSearch = {}) { }
}