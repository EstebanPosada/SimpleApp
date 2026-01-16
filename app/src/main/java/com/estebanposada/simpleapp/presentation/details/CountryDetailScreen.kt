package com.estebanposada.simpleapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.estebanposada.simpleapp.R

@Composable
fun CountryDetailScreen(
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    Column() {
        LaunchedEffect(state) {
            if (state.isLoading) {
                viewModel.event(CountryDetailEvent.LoadCountryData)
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
        state.details?.let {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current).data(it.flags.svg)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "imageDesc"
            )
            Text(text = it.name.official, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(text = "Capitals:")
            it.capital.forEach { capital ->
                Text(text = capital)
            }
            it.languages.forEach { lang ->
                Text(text = lang)
            }
        }
    }
}
