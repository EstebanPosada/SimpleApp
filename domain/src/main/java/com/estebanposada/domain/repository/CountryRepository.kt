package com.estebanposada.domain.repository

import com.estebanposada.domain.model.CountryItemModel

interface CountryRepository {
    suspend fun getCountryList(): List<CountryItemModel>
    suspend fun getCountryDetail(): CountryItemModel
}