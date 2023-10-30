package com.ipirangad3v.rockpaperscissors.data.remote

import com.ipirangad3v.rockpaperscissors.data.entitities.GameResponse
import com.ipirangad3v.rockpaperscissors.data.entitities.NameResponse
import retrofit2.Call
import retrofit2.http.GET

interface RockPaperScissorsApi {

    @GET("books")
    fun getName(): Call<NameResponse>

    @GET("medieval_name")
    fun getPlay(): Call<GameResponse>
}