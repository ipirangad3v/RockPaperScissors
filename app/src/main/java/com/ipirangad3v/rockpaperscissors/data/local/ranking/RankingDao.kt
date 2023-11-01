package com.ipirangad3v.rockpaperscissors.data.local.ranking

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.ipirangad3v.rockpaperscissors.data.local.ranking.entities.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RankingDao {

    @Insert(onConflict = REPLACE)
    fun insertMatch(match: MatchEntity)

    @Query("SELECT * FROM matches")
    fun getAllMatches(): Flow<List<MatchEntity>>

}
