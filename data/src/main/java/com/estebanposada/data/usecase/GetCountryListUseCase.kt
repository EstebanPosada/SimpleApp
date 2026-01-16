package com.estebanposada.data.usecase

import com.estebanposada.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryListUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke() = repository.getCountryList()
}