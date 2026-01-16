package com.estebanposada.simpleapp.presentation.details

sealed class CountryDetailEvent {
    data object LoadCountryData : CountryDetailEvent()
}