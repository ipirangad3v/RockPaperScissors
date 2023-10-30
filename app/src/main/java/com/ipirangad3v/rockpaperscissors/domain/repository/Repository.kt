package com.ipirangad3v.rockpaperscissors.domain.repository

import com.ipirangad3v.rockpaperscissors.domain.models.Resource
import retrofit2.Call

interface Repository {

    fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
    ): Resource<out R> {
        return try {
            val response = call.execute()
            val either = when (response.isSuccessful) {
                true  -> Resource.success(transform((response.body()!!)))
                false -> Resource.error("Server Error", null)
            }
            either
        } catch (exception: Throwable) {
            Resource.error(exception.message ?: "Error", null)
        }
    }
}