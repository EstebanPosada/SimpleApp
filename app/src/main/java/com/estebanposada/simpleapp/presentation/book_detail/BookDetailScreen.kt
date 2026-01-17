package com.estebanposada.simpleapp.presentation.book_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.estebanposada.simpleapp.R
import com.estebanposada.simpleapp.presentation.book_detail.components.Chip
import com.estebanposada.simpleapp.presentation.book_detail.components.Tag
import com.estebanposada.simpleapp.presentation.ui.theme.Purple40
import com.estebanposada.simpleapp.presentation.ui.theme.PurpleGrey80
import kotlin.math.round


@Composable
fun BookDetailScreen(
    viewModel: BookDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.state.value
    BookDetail(state, onBack = onBack)
}

@Composable
fun BookDetail(state: BookDetailState, onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        state.book?.let { book ->
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxWidth()
                        .background(Purple40)
                ) {
                    IconButton(
                        onClick = onBack, modifier = Modifier
                            .align(Alignment.TopStart)
                            .statusBarsPadding()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(
                            LocalContext.current
                        ).data(book.imageUrl).crossfade(true)
                            .placeholder(R.drawable.book_placeholder)
                            .error(R.drawable.book_error).build()
                    )
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Book cover"
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(0.7f)
                        .fillMaxWidth()
                        .background(PurpleGrey80)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.headlineMedium,
                            maxLines = 2,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = book.subject_people?.joinToString() ?: "",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            book.rating?.let {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "Rating",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Chip {
                                        Text(text = "${round(book.rating * 10) / 10.0}")
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = "rating"
                                        )
                                    }
                                }
                            }
                        }
                        if (book.languages.isNotEmpty()) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "Languages",
                                    style = MaterialTheme.typography.titleSmall
                                )
                                FlowRow(
                                    modifier = Modifier.wrapContentSize(Alignment.Center),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    book.languages.forEach { lang ->
                                        Tag(modifier = Modifier.padding(2.dp),
                                            text = lang.uppercase())
                                    }
                                }
                            }
                        }
                        Text(text = "Synopsis", style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
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
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
private fun BookDetailPreview() {
    BookDetail(state = BookDetailState()) {}
}