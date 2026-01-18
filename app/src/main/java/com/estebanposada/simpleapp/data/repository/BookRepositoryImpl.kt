package com.estebanposada.simpleapp.data.repository

import coil.network.HttpException
import com.estebanposada.simpleapp.common.ErrorType
import com.estebanposada.simpleapp.common.Resource
import com.estebanposada.simpleapp.data.remote.BookApi
import com.estebanposada.simpleapp.data.remote.mapper.toBook
import com.estebanposada.simpleapp.data.remote.mapper.toBookDetail
import com.estebanposada.simpleapp.domain.model.Book
import com.estebanposada.simpleapp.domain.model.BookDetail
import com.estebanposada.simpleapp.domain.repository.BookRepository
import okio.IOException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
) : BookRepository {
    override suspend fun getBooks(q: String): Resource<List<Book>> = try {
        val response = api.searchBook(q)
        Resource.Success(response.docs.map { it.toBook() })
    } catch (e: IOException) {
        Resource.Error(error = ErrorType.NETWORK, cause = e)
    } catch (e: HttpException) {
        Resource.Error(error = ErrorType.HTTP_ERROR, cause = e)
    } catch (e: Exception) {
        Resource.Error(error = ErrorType.UNKNOWN, cause = e)
    }

    override suspend fun getBookById(id: String): Resource<BookDetail> = try {
        val response = api.getDescription(id)
        Resource.Success(response.toBookDetail())
    } catch (e: IOException) {
        Resource.Error(error = ErrorType.NETWORK, cause = e)
    } catch (e: HttpException) {
        Resource.Error(error = ErrorType.HTTP_ERROR, cause = e)
    } catch (e: Exception) {
        Resource.Error(error = ErrorType.UNKNOWN, cause = e)
    }
}