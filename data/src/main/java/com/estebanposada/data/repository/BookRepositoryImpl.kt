package com.estebanposada.data.repository

import com.estebanposada.data.local.dao.BookDao
import com.estebanposada.data.remote.api.BookApi
import com.estebanposada.data.remote.mapper.toBook
import com.estebanposada.data.remote.mapper.toBookEntity
import com.estebanposada.domain.model.Book
import com.estebanposada.domain.repository.BookRepository
import com.estebanposada.domain.util.ErrorType
import com.estebanposada.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
    private val dao: BookDao
) : BookRepository {
    override suspend fun getBooks(q: String): Resource<List<Book>> = try {
        val response = api.searchBook(q)
        val booksDto = response.docs.map { it.toBookEntity() }
        dao.insertAll(booksDto)
        val books = booksDto.map { it.toBook() }
        Resource.Success(books)
    } catch (e: IOException) {
        e.printStackTrace()
        Resource.Error(error = ErrorType.NETWORK, cause = e)
    } catch (e: HttpException) {
        e.printStackTrace()
        Resource.Error(error = ErrorType.HTTP_ERROR, cause = e)
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Error(error = ErrorType.UNKNOWN, cause = e)
    }

    override suspend fun getBookById(id: String): Resource<Book> = try {
        val response = api.getDescription(id)
        dao.updateBook(
            id = id,
            description = response.description ?: "",
            links = response.links?.map { it.url } ?: emptyList())
        val book = dao.getBookById(id).toBook()
        Resource.Success(book)
    } catch (e: IOException) {
        e.printStackTrace()
        Resource.Error(error = ErrorType.NETWORK, cause = e)
    } catch (e: HttpException) {
        e.printStackTrace()
        Resource.Error(error = ErrorType.HTTP_ERROR, cause = e)
    } catch (e: Exception) {
        e.printStackTrace()
        Resource.Error(error = ErrorType.UNKNOWN, cause = e)
    }
}