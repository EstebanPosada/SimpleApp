package usecase.get_books

import book
import com.estebanposada.domain.repository.BookRepository
import com.estebanposada.domain.usecase.get_books.GetBooksUseCase
import com.estebanposada.domain.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
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

        assertTrue(result is Resource.Error)
    }

    @Test
    fun `when getBooksUseCase is called, then return successful`() = runTest {
        // Given
        val q = "q"
        val someBooks = listOf(book)
        val response = Resource.Success(someBooks)

        // When
        coEvery { repository.getBooks(q) } returns response
        val result = getBooksUseCase(q)

        assertEquals(result, Resource.Success(someBooks))
    }
}