package com.estebanposada.simpleapp.presentation.book_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
        when {
            state.error != null -> Text(
                text = state.error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )

            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            state.book != null -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxWidth()
                            .background(Purple40)
                            .clipToBounds()
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
                            ).data(state.book.imageUrl).crossfade(true)
                                .placeholder(R.drawable.book_placeholder)
                                .error(R.drawable.book_error).build()
                        )
                        Image(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxHeight()
                                .aspectRatio(3f / 4f),
                            painter = painter,
                            contentScale = ContentScale.Fit,
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
                                text = state.book.title,
                                style = MaterialTheme.typography.headlineMedium,
                                maxLines = 2,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = state.book.authors.joinToString(", "),
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                modifier = Modifier.padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                state.book.rating?.let {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = "Rating",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                        Chip {
                                            Text(text = "${round(it * 10) / 10.0}")
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "rating"
                                            )
                                        }
                                    }
                                }
                            }
                            state.book.languages?.let {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "Languages",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    FlowRow(
                                        modifier = Modifier.wrapContentSize(Alignment.Center),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        it.forEach { lang ->
                                            Tag(
                                                modifier = Modifier.padding(2.dp),
                                                text = lang.uppercase()
                                            )
                                        }
                                    }
                                }
                            }
                            Text(text = "Synopsis", style = MaterialTheme.typography.titleLarge)
                            Text(
                                text = state.book.description
                                    ?: stringResource(R.string.unavailable),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BookDetailPreview() {
    BookDetail(state = BookDetailState()) {}
}