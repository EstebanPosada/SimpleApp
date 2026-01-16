package com.estebanposada.simpleapp.presentation.list

sealed class CountryListEvent {
    data object LoadCountryList: CountryListEvent()
    data class CountryClicked(val id: String): CountryListEvent()
}