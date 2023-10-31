package com.ipirangad3v.rockpaperscissors.domain.repository

import com.ipirangad3v.rockpaperscissors.data.remote.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.data.remote.entitities.NameResponse
import com.ipirangad3v.rockpaperscissors.data.remote.RockPaperScissorsService
import com.ipirangad3v.rockpaperscissors.domain.models.Resource
import com.ipirangad3v.rockpaperscissors.domain.platform.NetworkHandler
import javax.inject.Inject

interface GameRepository : Repository {

    fun getPlay(): Resource<GameResponse>
    fun getName(): Resource<NameResponse>

    class Impl
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: RockPaperScissorsService,
    ) : GameRepository {

        override fun getPlay(): Resource<GameResponse> {
            return when (networkHandler.isNetworkAvailable()) {
                true  ->
                    request(
                        service.getPlay()
                    ) { gameResponse ->
                        gameResponse
                    }

                false -> Resource.error("No internet connection", null)
            }
        }

        override fun getName(): Resource<NameResponse> {
            return when (networkHandler.isNetworkAvailable()) {
                true  ->
                    request(
                        service.getName()
                    ) { gameResponse ->
                        gameResponse
                    }

                false -> Resource.error("No internet connection", null)
            }
        }
    }

}