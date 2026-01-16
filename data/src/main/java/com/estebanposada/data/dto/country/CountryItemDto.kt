package com.estebanposada.data.dto.country

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryItemDto(
    @SerialName("capital")
    val capital: List<String>,
    @SerialName("flags")
    val flags: Flags,
    @SerialName("languages")
    val languages: List<String>,
    @SerialName("name")
    val name: Name
)