package com.estebanposada.simpleapp.presentation.list

sealed class CountryListSideEffect {
    data class NavigateToDetail(val id: String) : CountryListSideEffect()
}