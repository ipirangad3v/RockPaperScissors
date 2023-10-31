package com.ipirangad3v.rockpaperscissors.domain.models

enum class Movement {
    ROCK, PAPER, SCISSORS;

    companion object {
        fun String?.getMovement(): Movement {
            return when (this) {
                "rock"     -> ROCK
                "paper"    -> PAPER
                "scissors" -> SCISSORS
                else       -> throw Exception("Invalid movement")
            }
        }
    }
}