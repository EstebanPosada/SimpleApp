package com.estebanposada.simpleapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey
    val id: String,
    val authors: List<String>,
    val imageUrl: String,
    val languages: List<String>,
    val publishYear: String,
    val rating: Double,
    val title: String,
    val description: String?,
    val links: List<String>,
)