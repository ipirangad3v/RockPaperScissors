package com.ipirangad3v.rockpaperscissors.ui.screens.ranking

import com.ipirangad3v.rockpaperscissors.data.local.ranking.entities.MatchEntity

data class RankingScreenState(
    val loading: Boolean = false,
    val matches: List<MatchEntity> = emptyList(),
)