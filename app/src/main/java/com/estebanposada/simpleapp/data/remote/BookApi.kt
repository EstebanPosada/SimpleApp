package com.estebanposada.simpleapp.data.remote

import com.estebanposada.simpleapp.data.remote.dto.SearchDto
import com.estebanposada.simpleapp.data.remote.dto.test.BookDetailDto
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