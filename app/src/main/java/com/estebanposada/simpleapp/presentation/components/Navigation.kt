package com.estebanposada.simpleapp.presentation.components

import kotlinx.serialization.Serializable

@Serializable
object Navigation

data class CountryDetails(
    val name: String
)

enum class NavigationState{
    HOME, DETAILS
}

sealed class Screen(val route: String){
    object CountryListScreen: Screen("CountryList")
    object CountryDetailScreen: Screen("CountryDetail")
}