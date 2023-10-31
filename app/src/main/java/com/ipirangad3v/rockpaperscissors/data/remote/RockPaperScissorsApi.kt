package com.ipirangad3v.rockpaperscissors.data.remote

import com.ipirangad3v.rockpaperscissors.data.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.data.entitities.NameResponse
import retrofit2.Call
import retrofit2.http.GET

interface RockPaperScissorsApi {

    @GET("medieval_name")
    fun getName(): Call<NameResponse>

    @GET("rock_paper_scissors")
    fun getPlay(): Call<GameResponse>
}