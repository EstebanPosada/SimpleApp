package com.estebanposada.data.repository

import com.estebanposada.data.mapper.CountryMapper
import com.estebanposada.data.remote.api.CountryApi
import com.estebanposada.domain.model.CountryItemModel
import com.estebanposada.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi,
//    private val mapper: CountryMapper
) : CountryRepository {
    override suspend fun getCountryList(): List<CountryItemModel> = TODO()
//        mapper.map(api.getCountries())
    override suspend fun getCountryDetail(): CountryItemModel = TODO()
//        mapper.map(api.getCountries()).first()

}