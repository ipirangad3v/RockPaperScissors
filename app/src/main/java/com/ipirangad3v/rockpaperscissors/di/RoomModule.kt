package com.ipirangad3v.rockpaperscissors.di

import android.app.Application
import androidx.room.Room
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDao
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase.Companion.RANKING_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesRankingDatabase(application: Application): RankingDatabase = Room
        .databaseBuilder(application, RankingDatabase::class.java, RANKING_DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesRankingDao(database: RankingDatabase): RankingDao = database.rankingDao()
}