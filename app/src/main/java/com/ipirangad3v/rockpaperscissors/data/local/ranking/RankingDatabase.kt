package com.ipirangad3v.rockpaperscissors.data.local.ranking

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase.Companion.RANKING_DB_VERSION
import javax.inject.Singleton
import com.ipirangad3v.rockpaperscissors.data.local.ranking.entities.MatchEntity
import com.ipirangad3v.rockpaperscissors.domain.models.RoundResult

@Singleton
@Database(
    entities =
    [
        MatchEntity::class,
    ],
    version = RANKING_DB_VERSION,
    exportSchema = false
)

abstract class RankingDatabase : RoomDatabase() {
    abstract fun rankingDao(): RankingDao

    companion object {
        const val RANKING_DB_VERSION = 1
        const val RANKING_DB_NAME = "ranking"
        const val MATCHES_TABLE = "matches"
    }
}