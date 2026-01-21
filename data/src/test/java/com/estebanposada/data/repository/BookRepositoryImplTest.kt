package com.estebanposada.data.repository

import com.estebanposada.data.bookDetailDto
import com.estebanposada.data.bookDto
import com.estebanposada.data.bookEntity
import com.estebanposada.data.local.dao.BookDao
import com.estebanposada.data.remote.api.BookApi
import com.estebanposada.data.remote.dto.SearchDto
import com.estebanposada.data.remote.mapper.toBook
import com.estebanposada.data.remote.mapper.toBookEntity
import com.estebanposada.domain.repository.BookRepository
import com.estebanposada.domain.util.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class BookRepositoryImplTest {
    private lateinit var repository: BookRepository
    private lateinit var api: BookApi
    private lateinit var dao: BookDao

    @Before
    fun setUp() {
        api = mockk()
        dao = mockk()
        repository = BookRepositoryImpl(api, dao)
    }

    @Test
    fun `when searchBook is called, then api throws error`() = runTest {
        val q = ""

        coEvery { api.searchBook(q) } throws IOException()
        val result = repository.getBooks(q)

        assertThat(result).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `when searchBook is called, then return successful`() = runTest {
        val q = ""
        val dto = bookDto
        val books = listOf(dto.toBookEntity())
        val response = SearchDto(docs = listOf(dto))

        coEvery { api.searchBook(q) } returns response

        coEvery { dao.insertAll(any()) } just Runs
        val result = repository.getBooks(q)

        assertThat(result).isInstanceOf(Resource.Success::class.java)
        val data = (result as Resource.Success).data
        assertThat(data).hasSize(1)
        assertThat(data.first()).isEqualTo(bookDto.toBook())
    }

    @Test
    fun `when getBookById is called, then api throws error`() = runTest {
        val id = ""

        coEvery { api.getDescription(id) } throws IOException()
        val result = repository.getBookById(id)

        assertThat(result).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `when getBookById is called, then return successful`() = runTest {
        val id = ""
        val bookEntity = bookEntity
        val response = bookDetailDto

        coEvery { api.getDescription(id) } returns response

        coEvery { dao.updateBook(any(), any(), any()) } just Runs
        coEvery { dao.getBookById(id) } returns bookEntity
        val result = repository.getBookById(id)

        assertThat(result).isInstanceOf(Resource.Success::class.java)
        val data = (result as Resource.Success).data
        assertThat(data).isEqualTo(bookEntity.toBook())
    }
}