package com.estebanposada.data.remote.api

import com.estebanposada.data.remote.dto.BookDetailDto
import com.estebanposada.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {
    @GET("search.json")
    suspend fun searchBook(
        @Query("q") q: String,
        @Query("limit") limit: Int = 15,
        @Query("fields") fields: String = "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count"
    ): SearchDto

    @GET("works/{bookId}.json")
    suspend fun getDescription(@Path("bookId") bookId: String): BookDetailDto
}