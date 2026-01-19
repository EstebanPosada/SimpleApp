package com.estebanposada.simpleapp.data.repository

import coil.network.HttpException
import com.estebanposada.simpleapp.domain.util.ErrorType
import com.estebanposada.simpleapp.domain.util.Resource
import com.estebanposada.simpleapp.data.local.dao.BookDao
import com.estebanposada.simpleapp.data.remote.api.BookApi
import com.estebanposada.simpleapp.data.remote.mapper.toBook
import com.estebanposada.simpleapp.data.remote.mapper.toBookEntity
import com.estebanposada.simpleapp.domain.model.Book
import com.estebanposada.simpleapp.domain.repository.BookRepository
import okio.IOException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
    private val dao: BookDao
) : BookRepository {
    override suspend fun getBooks(q: String): Resource<List<Book>> = try {
        val response = api.searchBook(q)
        val data = response.docs.map { it.toBookEntity() }
        dao.insertAll(data)
        Resource.Success(response.docs.map { it.toBook() })
    } catch (e: IOException) {
        Resource.Error(error = ErrorType.NETWORK, cause = e)
    } catch (e: HttpException) {
        Resource.Error(error = ErrorType.HTTP_ERROR, cause = e)
    } catch (e: Exception) {
        Resource.Error(error = ErrorType.UNKNOWN, cause = e)
    }

    override suspend fun getBookById(id: String): Resource<Book> = try {
        val response = api.getDescription(id)
        dao.updateBook(
            id,
            response.description ?: "",
            response.links?.map { it.url } ?: emptyList())
        val book = dao.getBookById(id)
        println("Getting: $book")
        Resource.Success(book.toBook())
    } catch (e: IOException) {
        Resource.Error(error = ErrorType.NETWORK, cause = e)
    } catch (e: HttpException) {
        Resource.Error(error = ErrorType.HTTP_ERROR, cause = e)
    } catch (e: Exception) {
        Resource.Error(error = ErrorType.UNKNOWN, cause = e)
    }
}