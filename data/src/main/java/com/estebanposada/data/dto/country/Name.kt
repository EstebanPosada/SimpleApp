package com.estebanposada.data.dto.country


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    @SerialName("common")
    val common: String,
    @SerialName("nativeName")
    val nativeName: NativeName? = null,
    @SerialName("official")
    val official: String
)