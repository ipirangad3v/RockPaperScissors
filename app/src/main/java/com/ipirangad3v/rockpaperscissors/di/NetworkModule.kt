package com.ipirangad3v.rockpaperscissors.di

import com.ipirangad3v.rockpaperscissors.data.remote.RockPaperScissorsApi
import com.ipirangad3v.rockpaperscissors.domain.common.constants.BASE_URL
import com.ipirangad3v.rockpaperscissors.domain.common.constants.SOCKET_TIMEOUT
import com.ipirangad3v.rockpaperscissors.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun createGameService(retrofit: Retrofit): RockPaperScissorsApi =
        retrofit.create(RockPaperScissorsApi::class.java)

    @Provides
    @Singleton
    fun provideGameRepository(dataSource: GameRepository.Impl): GameRepository =
        dataSource

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(SOCKET_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.readTimeout(SOCKET_TIMEOUT, TimeUnit.MILLISECONDS)

        return okHttpClientBuilder.build()
    }
}
