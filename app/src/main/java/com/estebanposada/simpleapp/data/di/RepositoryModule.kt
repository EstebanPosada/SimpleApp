package com.estebanposada.simpleapp.data.di

import com.estebanposada.simpleapp.data.local.dao.BookDao
import com.estebanposada.simpleapp.data.remote.api.BookApi
import com.estebanposada.simpleapp.data.repository.BookRepositoryImpl
import com.estebanposada.simpleapp.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideBookRepository(api: BookApi, dao: BookDao): BookRepository =
        BookRepositoryImpl(api, dao)
}