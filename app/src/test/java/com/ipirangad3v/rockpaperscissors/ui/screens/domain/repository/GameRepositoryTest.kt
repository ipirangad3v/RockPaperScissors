package com.ipirangad3v.rockpaperscissors.ui.screens.domain.repository

import com.ipirangad3v.rockpaperscissors.data.remote.RockPaperScissorsService
import com.ipirangad3v.rockpaperscissors.data.remote.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.data.remote.entitities.NameResponse
import com.ipirangad3v.rockpaperscissors.domain.platform.NetworkHandler
import com.ipirangad3v.rockpaperscissors.domain.repository.GameRepository
import okhttp3.Request
import okio.Timeout
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameRepositoryTest {

    @Mock
    private lateinit var networkHandler: NetworkHandler

    @Mock
    private lateinit var service: RockPaperScissorsService

    private lateinit var gameRepository: GameRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gameRepository = GameRepository.Impl(networkHandler, service)
    }

    @Test
    fun `getPlay() should return Resource with gameResponse when network is available`() {
        // Arrange
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)
        val mockGameResponse = GameResponse(
            cpu = "cpu",
            player = "player",
            winner = "winner",
        )
        `when`(service.getPlay()).thenReturn(CallFake(mockGameResponse))

        // Act
        val result = gameRepository.getPlay()

        // Assert
        verify(networkHandler).isNetworkAvailable()
        verify(service).getPlay()
        assert(result.data == mockGameResponse)
        assert(result.message == null)
    }

    @Test
    fun `getPlay() should return Resource with error message when network is unavailable`() {
        // Arrange
        `when`(networkHandler.isNetworkAvailable()).thenReturn(false)

        // Act
        val result = gameRepository.getPlay()

        // Assert
        verify(networkHandler).isNetworkAvailable()
        verify(service, never()).getPlay()
        assert(result.data == null)
        assert(result.message == "No internet connection")
    }

    @Test
    fun `getName() should return Resource with nameResponse when network is available`() {
        // Arrange
        `when`(networkHandler.isNetworkAvailable()).thenReturn(true)
        val mockNameResponse = NameResponse(
            results = listOf("name")
        )
        `when`(service.getName()).thenReturn(CallFake(mockNameResponse))

        // Act
        val result = gameRepository.getName()

        // Assert
        verify(networkHandler).isNetworkAvailable()
        verify(service).getName()
        assert(result.data == mockNameResponse)
        assert(result.message == null)
    }

    @Test
    fun `getName() should return Resource with error message when network is unavailable`() {
        // Arrange
        `when`(networkHandler.isNetworkAvailable()).thenReturn(false)

        // Act
        val result = gameRepository.getName()

        // Assert
        verify(networkHandler).isNetworkAvailable()
        verify(service, never()).getName()
        assert(result.data == null)
        assert(result.message == "No internet connection")
    }
}

private class CallFake<T>(private val response: T) : Call<T> {
    override fun clone(): Call<T> {
        return this
    }

    override fun execute(): Response<T> {
        return Response.success(response)
    }

    override fun isExecuted(): Boolean {
        return true
    }

    override fun cancel() {

    }

    override fun isCanceled(): Boolean {
        return false
    }

    override fun request(): Request {
        return Request.Builder().build()
    }

    override fun timeout(): Timeout {
        return Timeout()
    }

    override fun enqueue(callback: Callback<T>) {

    }
}