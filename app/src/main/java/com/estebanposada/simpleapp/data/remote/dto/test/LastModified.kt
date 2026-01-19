package com.estebanposada.simpleapp.data.remote.dto.test

import kotlinx.serialization.Serializable

@Serializable
data class LastModified(
    val type: String,
    val value: String
)