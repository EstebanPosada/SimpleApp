package com.estebanposada.simpleapp.domain.usecase.get_books

import com.estebanposada.simpleapp.bookDto
import com.estebanposada.simpleapp.domain.util.Resource
import com.estebanposada.simpleapp.data.remote.mapper.toBook
import com.estebanposada.simpleapp.domain.repository.BookRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetBooksUseCaseTest {

    private lateinit var getBooksUseCase: GetBooksUseCase
    private lateinit var repository: BookRepository

    @Before
    fun setUp() {
        repository = mockk()
        getBooksUseCase = GetBooksUseCase(repository)
    }

    @Test
    fun `when getBooksUseCase is called and returns error`() = runTest {
        val q = ""

        coEvery { repository.getBooks(q) } returns Resource.Error()
        val result = getBooksUseCase(q)

        assertThat(result).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `when getBooksUseCase is called, then return successful`() = runTest {
        // Given
        val q = "q"
        val someBooks = listOf(bookDto.toBook())
        val response = Resource.Success(someBooks)

        // When
        coEvery { repository.getBooks(q) } returns response
        val result = getBooksUseCase(q)

        assertThat(result).isEqualTo(Resource.Success(someBooks))
    }
}