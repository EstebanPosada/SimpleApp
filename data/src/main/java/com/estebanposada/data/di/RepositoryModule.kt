package com.estebanposada.data.di

import com.estebanposada.data.remote.api.CountryApi
import com.estebanposada.data.repository.CountryRepositoryImpl
import com.estebanposada.data.usecase.GetCountryListUseCase
import com.estebanposada.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
object RepositoryModule {
    //    @Binds
//    abstract fun bindCountryRepository(countryRepositoryImpl: CountryRepositoryImpl): CountryRepository
    @Singleton
    @Provides
    fun provideCountryRepository(api: CountryApi): CountryRepository = CountryRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideGetCountryList(repository: CountryRepository) = GetCountryListUseCase(repository)
}