package com.estebanposada.simpleapp.di

import com.estebanposada.data.local.dao.BookDao
import com.estebanposada.data.remote.api.BookApi
import com.estebanposada.data.repository.BookRepositoryImpl
import com.estebanposada.domain.repository.BookRepository
import com.estebanposada.domain.usecase.get_book_detail.GetBookDetailUseCase
import com.estebanposada.domain.usecase.get_books.GetBooksUseCase
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

    @Provides
    @Singleton
    fun provideGetBookUseCase(repository: BookRepository) = GetBooksUseCase(repository)

    @Provides
    @Singleton
    fun provideGetBookDetailUseCase(repository: BookRepository) = GetBookDetailUseCase(repository)
}