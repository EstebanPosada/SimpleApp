package com.estebanposada.simpleapp.presentation.list

import com.estebanposada.domain.model.CountryItemModel

sealed class CountryListState {
    data object Loading: CountryListState()
    data class Success(val countries: List<CountryItemModel>): CountryListState()
    data class Error(val error: String): CountryListState()
}