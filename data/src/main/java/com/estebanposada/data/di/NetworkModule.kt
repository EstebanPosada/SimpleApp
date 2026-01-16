package com.estebanposada.data.di

import com.estebanposada.data.remote.api.CountryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @BaseUrl
    fun provideUrl() = "https://restcountries.com/v3.1/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Singleton
    @Provides
    fun provideGsonCOnverterFactory() = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(@BaseUrl url: String, okHttpClient: OkHttpClient, factory: GsonConverterFactory) =
        Retrofit.Builder().baseUrl(url).client(okHttpClient).addConverterFactory(factory).build()

    @Singleton
    @Provides
    fun provideCountryApi(retrofit: Retrofit) = retrofit.create(CountryApi::class.java)
}