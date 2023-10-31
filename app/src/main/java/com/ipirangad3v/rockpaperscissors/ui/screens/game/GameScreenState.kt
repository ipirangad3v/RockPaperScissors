package com.ipirangad3v.rockpaperscissors.ui.screens.game

import com.ipirangad3v.rockpaperscissors.domain.models.GameState

data class GameScreenState(
    val currentUserName: String = "",
    val currentFoeName: String = "",
    val currentGameState: GameState = GameState.IDLE,
    val loading: Boolean = false,
)