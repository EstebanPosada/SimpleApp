package com.estebanposada.simpleapp.data.di

import android.content.Context
import androidx.room.Room
import com.estebanposada.simpleapp.data.local.BookDatabase
import com.estebanposada.simpleapp.data.local.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase =
        Room.databaseBuilder(context, BookDatabase::class.java, "s_book_db").build()

    @Provides
    fun provideDao(db: BookDatabase): BookDao = db.bookDao()
}