package usecase.get_book_detail

import book
import com.estebanposada.domain.repository.BookRepository
import com.estebanposada.domain.usecase.get_book_detail.GetBookDetailUseCase
import com.estebanposada.domain.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
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

        assertTrue(result is Resource.Error)
    }

    @Test
    fun `when getBookDetailUseCase is called, then return successful`() = runTest {
        // Given
        val bookDto = book
        val response = Resource.Success(bookDto)
        val id = "id"

        // When
        coEvery { repository.getBookById(id) } returns response
        val result = getBookDetailUseCase(id)

        assertEquals(result, Resource.Success(bookDto))
    }
}