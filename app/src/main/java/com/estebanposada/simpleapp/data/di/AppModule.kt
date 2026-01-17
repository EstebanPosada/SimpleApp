package com.estebanposada.simpleapp.data.di

import com.estebanposada.simpleapp.BuildConfig
import com.estebanposada.simpleapp.data.remote.BookApi
import com.estebanposada.simpleapp.data.repository.BookRepositoryImpl
import com.estebanposada.simpleapp.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideBookApi(client: OkHttpClient, factory: GsonConverterFactory): BookApi =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(client).addConverterFactory(factory).build().create()

    @Provides
    @Singleton
    fun provideBookRepository(api: BookApi): BookRepository = BookRepositoryImpl(api)
}