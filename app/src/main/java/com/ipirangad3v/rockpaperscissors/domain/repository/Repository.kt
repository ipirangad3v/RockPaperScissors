package com.ipirangad3v.rockpaperscissors.domain.repository

import com.ipirangad3v.rockpaperscissors.domain.models.Resource
import retrofit2.Call

interface Repository {

    fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R
    ): Resource<R> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(transform(body))
                }
            }
            return Resource.error("Server Error", null)
        } catch (exception: Throwable) {
            return Resource.error(exception.message ?: "Error", null)
        }
    }
}