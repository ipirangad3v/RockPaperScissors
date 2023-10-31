package com.ipirangad3v.rockpaperscissors.data.remote

import com.ipirangad3v.rockpaperscissors.data.remote.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.data.remote.entitities.NameResponse
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Call
import retrofit2.Retrofit

@Singleton
class RockPaperScissorsService
@Inject constructor(retrofit: Retrofit) : RockPaperScissorsApi {
    private val api by lazy { retrofit.create(RockPaperScissorsApi::class.java) }
    override fun getName(): Call<NameResponse> = api.getName()

    override fun getPlay(): Call<GameResponse> = api.getPlay()

}