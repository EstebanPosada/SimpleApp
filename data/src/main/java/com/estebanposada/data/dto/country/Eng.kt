package com.estebanposada.data.dto.country


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Eng(
    @SerialName("common")
    val common: String,
    @SerialName("official")
    val official: String
)