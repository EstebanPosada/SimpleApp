package com.estebanposada.simpleapp.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.estebanposada.data.dto.country.CountryItemDto
import com.estebanposada.data.dto.country.Flags
import com.estebanposada.data.dto.country.Name
import com.estebanposada.data.dto.country.NativeName
import com.estebanposada.domain.model.CountryItemModel
import com.estebanposada.simpleapp.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CountryListScreen(
    viewModel: CountryListViewModel = hiltViewModel(),
    onItemClick: (id: String) -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val dispatch: (CountryListEvent) -> Unit = { event ->
        viewModel.event(event)
    }

    LaunchedEffect(state) {
        if (state is CountryListState.Success) {
            viewModel.event(CountryListEvent.LoadCountryList)
        }
    }
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest {
            when (it) {
                is CountryListSideEffect.NavigateToDetail -> onItemClick(it.id)
            }
        }
    }
    when (state) {
        is CountryListState.Error -> TODO()
        CountryListState.Loading -> TODO()
        is CountryListState.Success -> TODO()
    }
}

@Composable
private fun CountryListUi(
    state: CountryListState.Success,
    onAction: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(state.countries, key = { it.name }) {
            CountryItem(
                name = it.name,
                capital = it.capital.first(),
                language = it.languages.first(),
                imageUrl = it.flags,
                imageDesc = it.flags
            )
        }
    }
}

@Composable
private fun CountryItem(
    imageUrl: String,
    name: String,
    capital: String,
    language: String,
    imageDesc: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = imageDesc
        )
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(text = capital)
            Text(text = language)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryItemPreview() {
    MaterialTheme {
        CountryItem(
            name = "name",
            capital = "capital",
            language = "language",
            imageUrl = "",
            imageDesc = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryListPreview() {
    val countries = (1..5).map {
        CountryItemModel(
            capital = listOf("Capital"),
            flags = "Flags",
            languages = listOf("Langs"),
            name = "Name $it"
        )
        /*CountryItemDto(
            name = Name(
                official = "Country $it",
                common = "Common $it"
            ),
            capital = listOf("Capital $it"),
            languages = listOf("Lang $it"),
            flags = Flags(
                alt = "alt",
                png = "png",
                svg = "http://test.com"
            )
        )*/
    }
    MaterialTheme {
        CountryListUi(state = CountryListState.Success(countries)) { }
    }
}