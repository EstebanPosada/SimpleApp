package com.estebanposada.data.dto.country


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NativeName(
    @SerialName("eng")
    val eng: Eng
)