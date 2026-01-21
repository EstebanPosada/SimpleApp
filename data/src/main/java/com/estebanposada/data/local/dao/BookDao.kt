package com.estebanposada.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.estebanposada.data.local.entity.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

//    @Query("SELECT * FROM BookEntity WHERE title LIKE '%' || :q || '%'")
//    suspend fun getBooks(q: String): List<BookEntity>

    @Query("SELECT * FROM BookEntity WHERE id LIKE :id ")
    suspend fun getBookById(id: String): BookEntity

    @Query("UPDATE BookEntity SET description = :description, links = :links WHERE id =:id")
    suspend fun updateBook(id: String, description: String, links: List<String>)
}