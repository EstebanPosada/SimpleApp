package com.estebanposada.simpleapp.presentation.details

import com.estebanposada.data.dto.country.CountryItemDto

data class CountryDetailState(
    val details: CountryItemDto? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)
