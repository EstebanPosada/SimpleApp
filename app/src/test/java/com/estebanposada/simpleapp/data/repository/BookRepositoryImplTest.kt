package com.estebanposada.simpleapp.data.repository

import com.estebanposada.simpleapp.bookDetailDto
import com.estebanposada.simpleapp.bookDto
import com.estebanposada.simpleapp.common.Resource
import com.estebanposada.simpleapp.data.remote.BookApi
import com.estebanposada.simpleapp.data.remote.dto.SearchDto
import com.estebanposada.simpleapp.data.remote.mapper.toBook
import com.estebanposada.simpleapp.data.remote.mapper.toBookDetail
import com.estebanposada.simpleapp.domain.repository.BookRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Before
import org.junit.Test

class BookRepositoryImplTest {
    private lateinit var repository: BookRepository
    private lateinit var api: BookApi

    @Before
    fun setUp() {
        api = mockk()
        repository = BookRepositoryImpl(api)
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
        val response = SearchDto(docs = listOf(dto))

        coEvery { api.searchBook(q) } returns response
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
        val response = bookDetailDto

        coEvery { api.getDescription(id) } returns response
        val result = repository.getBookById(id)

        assertThat(result).isInstanceOf(Resource.Success::class.java)
        val data = (result as Resource.Success).data
        assertThat(data).isEqualTo(response.toBookDetail())
    }
}