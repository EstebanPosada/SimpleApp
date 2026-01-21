package com.estebanposada.simpleapp.presentation.book_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.estebanposada.domain.model.Book
import com.estebanposada.simpleapp.R
import kotlin.math.round

@Composable
fun BookListItem(
    book: Book,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { onItemClick(book.id) }
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                LocalContext.current
            ).data(book.imageUrl).crossfade(true)
                .placeholder(R.drawable.book_placeholder)
                .error(R.drawable.book_error).build()
        )
        Image(
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(3f / 4f),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = "Book image cover"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            book.authors.firstOrNull()?.let { author ->
                Text(text = author, style = MaterialTheme.typography.bodyLarge)
            }
            book.rating?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${round(it * 10) / 10.0}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Icon(imageVector = Icons.Default.Star, contentDescription = "rating")
                }
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "arrow",
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview
@Composable
private fun BookListItemPreview() {
    val book = Book(
        id = "id",
        authors = listOf("Author"),
        imageUrl = "http://test.com",
        languages = listOf("lang"),
        publishYear = "1959",
        rating = 4.5,
        title = "Title",
        description = "Description"
    )
    BookListItem(book) { }
}