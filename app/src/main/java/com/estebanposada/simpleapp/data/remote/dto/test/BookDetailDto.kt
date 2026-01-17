package com.estebanposada.simpleapp.data.remote.dto.test

data class BookDetailDto(
    val authors: List<Author>,
    val created: Created,
    val key: String,
    val last_modified: LastModified,
    val latest_revision: Int,
    val links: List<Link>?,
    val revision: Int,
    val subject_people: List<String>?,
    val subject_places: List<String>?,
    val title: String,
    val type: TypeXX
)