package com.estebanposada.data.remote.api

import com.estebanposada.data.dto.country.CountryItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {
    @GET("all")
    suspend fun getCountries(@Query("fields") fields: String = "name,languages,capital,flags"): List<CountryItemDto>
}