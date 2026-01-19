package com.estebanposada.simpleapp.domain.usecase.get_book_detail

import com.estebanposada.simpleapp.bookDetailDto
import com.estebanposada.simpleapp.domain.util.Resource
import com.estebanposada.simpleapp.data.remote.mapper.toBookDetail
import com.estebanposada.simpleapp.domain.repository.BookRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetBookDetailUseCaseTest {
    private lateinit var getBookDetailUseCase: GetBookDetailUseCase
    private lateinit var repository: BookRepository

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        getBookDetailUseCase = GetBookDetailUseCase(repository)
    }

    @Test
    fun `when getBookDetailUseCase is called and returns error`() = runTest {
        val id = "id"

        coEvery { repository.getBookById(id) } returns Resource.Error()
        val result = getBookDetailUseCase(id)

        assertThat(result).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `when getBookDetailUseCase is called, then return successful`() = runTest {
        // Given
        val bookDetailDto = bookDetailDto.toBookDetail()
        val response = Resource.Success(bookDetailDto)
        val id = "id"

        // When
        coEvery { repository.getBookById(id) } returns response
        val result = getBookDetailUseCase(id)

        assertThat(result).isEqualTo(Resource.Success(bookDetailDto))
    }
}