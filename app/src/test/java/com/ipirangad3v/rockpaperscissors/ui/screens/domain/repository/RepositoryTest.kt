package com.ipirangad3v.rockpaperscissors.ui.screens.domain.repository

import com.ipirangad3v.rockpaperscissors.data.remote.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.domain.repository.Repository
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class RepositoryTest {

    @Mock
    private lateinit var call: Call<GameResponse>

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = object : Repository {}
    }

    @Test
    fun `request() should return Resource with data on successful call`() {
        // Arrange
        val mockResponse = Response.success(
            GameResponse(
                cpu = "cpu",
                player = "player",
                winner = "winner",
            )
        )
        `when`(call.execute()).thenReturn(mockResponse)

        // Act
        val result = repository.request(call) { it }

        // Assert
        assert(result.data != null)
        assert(result.data == mockResponse.body())
        assert(result.message == null)
    }

    @Test
    fun `request() should return Resource with error message on unsuccessful call`() {
        // Arrange
        val mockResponse = Response.error<GameResponse>(404, ResponseBody.create(null, "Not Found"))
        `when`(call.execute()).thenReturn(mockResponse)

        // Act
        val result = repository.request(call) { it }

        // Assert
        assert(result.data == null)
        assert(result.message == "Server Error")
    }

    @Test
    fun `request() should return Resource with error message on exception`() {
        // Arrange
        `when`(call.execute()).thenThrow(RuntimeException("Network error"))

        // Act
        val result = repository.request(call) { it }

        // Assert
        assert(result.data == null)
        assert(result.message == "Network error")
    }
}