package com.ipirangad3v.rockpaperscissors.domain.repository

import com.ipirangad3v.rockpaperscissors.data.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.data.entitities.NameResponse
import com.ipirangad3v.rockpaperscissors.data.remote.RockPaperScissorsService
import com.ipirangad3v.rockpaperscissors.domain.models.Resource
import com.ipirangad3v.rockpaperscissors.domain.platform.NetworkHandler
import javax.inject.Inject

interface GameRepository : Repository {

    fun getPlay(): Resource<out GameResponse>
    fun getName(): Resource<out NameResponse>

    class Impl
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: RockPaperScissorsService,
    ) : GameRepository {

        override fun getPlay(): Resource<out GameResponse> {
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

        override fun getName(): Resource<out NameResponse> {
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