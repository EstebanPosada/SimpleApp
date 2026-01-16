package com.estebanposada.data.mapper

import com.estebanposada.data.dto.country.CountryItemDto
import com.estebanposada.domain.mapper.ResultMapper
import com.estebanposada.domain.model.CountryItemModel
import javax.inject.Inject

class CountryMapper @Inject constructor() :
    ResultMapper<List<CountryItemDto>, List<CountryItemModel>> {
    override fun map(input: List<CountryItemDto>): List<CountryItemModel> =
        input.map { it.toModel() }

    private fun CountryItemDto.toModel() = CountryItemModel(
        capital = capital,
        flags = flags.svg,
        languages = languages,
        name = name.official,
    )
}