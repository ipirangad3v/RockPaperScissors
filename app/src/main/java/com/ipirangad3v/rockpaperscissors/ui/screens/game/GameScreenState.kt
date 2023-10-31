package com.ipirangad3v.rockpaperscissors.ui.screens.game

import com.ipirangad3v.rockpaperscissors.domain.models.GameState
import com.ipirangad3v.rockpaperscissors.domain.models.RoundResult

data class GameScreenState(
    val currentUserName: String = "",
    val currentFoeName: String = "",
    val currentGameState: GameState = GameState.IDLE,
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorMessage: String = "",
    val rounds: List<RoundResult> = emptyList(),
) {
    val finalResult: String?
        get() {
            if (rounds.size == 3 && findTwoEqualStrings(rounds) == null) {
                return "Draw"
            }
            findTwoEqualStrings(rounds).let {
                return it
            }
        }

    private fun findTwoEqualStrings(results: List<RoundResult>): String? {
        val seenStrings = mutableSetOf<String>()

        for (result in results) {
            if (seenStrings.contains(result.result)) {
                return result.result
            } else {
                seenStrings.add(result.result)
            }
        }

        return null
    }
}