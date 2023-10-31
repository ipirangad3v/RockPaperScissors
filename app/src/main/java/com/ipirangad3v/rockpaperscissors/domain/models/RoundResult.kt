package com.ipirangad3v.rockpaperscissors.domain.models

data class RoundResult(
    val result: String,
    val myMovement: Movement,
    val foeMovement: Movement,
    val currentRound: Int,
)