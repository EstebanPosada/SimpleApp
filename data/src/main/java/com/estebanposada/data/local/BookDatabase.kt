package com.estebanposada.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.estebanposada.data.local.converter.Converter
import com.estebanposada.data.local.dao.BookDao
import com.estebanposada.data.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converter::class)
    abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}