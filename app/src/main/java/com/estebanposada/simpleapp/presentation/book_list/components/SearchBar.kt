package com.estebanposada.simpleapp.presentation.book_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.estebanposada.simpleapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit, onSearch: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text(stringResource(R.string.type_book_name)) },
            singleLine = true,
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )
        IconButton(onClick = onSearch) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.content_desc_search)
            )
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(query = "", onQueryChange = {}) { }
}