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
        @Query("fields") fields: String = "key,title,author_name,edition"
    ): SearchDto

    @GET("works/{bookId}.json")
    suspend fun getDescription(@Path("bookId") bookId: String): BookDetailDto
}