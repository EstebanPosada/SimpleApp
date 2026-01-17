package com.estebanposada.simpleapp.domain.model

import com.estebanposada.simpleapp.data.remote.dto.test.LastModified
import com.estebanposada.simpleapp.data.remote.dto.test.Link

data class BookDetail(
    val id: String,
    val last_modified: LastModified,
    val latest_revision: Int,
    val links: List<Link>?,
    val revision: Int,
    val subject_people: List<String>?,
    val subject_places: List<String>?,
    val title: String,
    val imageUrl: String,
    val rating: Double?,
    val languages: List<String>
)
