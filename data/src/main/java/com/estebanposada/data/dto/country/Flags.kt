package com.estebanposada.data.dto.country


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    @SerialName("alt")
    val alt: String,
    @SerialName("png")
    val png: String,
    @SerialName("svg")
    val svg: String
)