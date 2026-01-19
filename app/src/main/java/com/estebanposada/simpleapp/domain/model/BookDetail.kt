package com.estebanposada.simpleapp.domain.model

import com.estebanposada.simpleapp.data.remote.dto.Link

data class BookDetail(
    val description: String?,
    val links: List<Link>?,
)
