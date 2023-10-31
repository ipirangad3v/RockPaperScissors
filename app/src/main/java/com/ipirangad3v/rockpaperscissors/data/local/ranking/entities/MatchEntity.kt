package com.ipirangad3v.rockpaperscissors.data.local.ranking.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase.Companion.MATCHES_TABLE

@Entity(tableName = MATCHES_TABLE)
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cpu: String,
    val player: String,
    val winner: String,
    val matches: Int,
)

