package com.ipirangad3v.rockpaperscissors.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipirangad3v.rockpaperscissors.data.local.datastore.PreferencesDataStore
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase
import com.ipirangad3v.rockpaperscissors.data.local.ranking.entities.MatchEntity
import com.ipirangad3v.rockpaperscissors.domain.models.GameState
import com.ipirangad3v.rockpaperscissors.domain.models.Movement
import com.ipirangad3v.rockpaperscissors.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.ipirangad3v.rockpaperscissors.domain.models.Movement.Companion.getMovement
import com.ipirangad3v.rockpaperscissors.domain.models.RoundResult

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val dataStore: PreferencesDataStore,
    private val rankingDatabase: RankingDatabase,
) : ViewModel() {

    private val _screenState = MutableStateFlow(GameScreenState()) // private mutable state flow
    val screenState = _screenState.asStateFlow() // publi

    private var currentRound = 1

    init {
        getUserName()
        getFoeName()
    }


    fun getPlay(movement: Movement) {
        if (_screenState.value.currentGameState != GameState.ENDED) {
            _screenState.value = _screenState.value.copy(
                loading = true
            )
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    defineWinner(movement, gameRepository.getPlay().data?.cpu.getMovement())
                }

            }
        }
    }

    fun resetGame() {
        _screenState.value = _screenState.value.copy(
            loading = true,
            currentGameState = GameState.IDLE,
            rounds = emptyList(),
            currentFoeName = "",
        )
        currentRound = 1
        getFoeName()
    }

    private fun defineWinner(myMovement: Movement, foeMovement: Movement) {

        if (_screenState.value.finalResult == null) {

            val result = when (myMovement) {
                foeMovement       -> "Draw"
                Movement.ROCK     -> if (foeMovement == Movement.SCISSORS) "Win" else "Lose"
                Movement.PAPER    -> if (foeMovement == Movement.ROCK) "Win" else "Lose"
                Movement.SCISSORS -> if (foeMovement == Movement.PAPER) "Win" else "Lose"
            }

            _screenState.value = _screenState.value.copy(
                loading = false,
                rounds = _screenState.value.rounds + RoundResult(
                    result,
                    myMovement,
                    foeMovement,
                    currentRound++
                )
            )
        } else {
            endGame()
        }

    }

    fun endGame() {
        _screenState.value = _screenState.value.copy(
            loading = false,
            currentGameState = GameState.ENDED
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                rankingDatabase.rankingDao().insertMatch(
                    MatchEntity(
                        cpu = _screenState.value.currentFoeName,
                        player = _screenState.value.currentUserName,
                        winner = _screenState.value.finalResult!!,
                        rounds = _screenState.value.rounds.size
                    )
                )
            }
        }
    }

    private fun getUserName() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataStore.readUserName().data
            }

            if (response != null) {
                _screenState.value = _screenState.value.copy(
                    currentUserName = response,
                    error = false,
                    errorMessage = "",
                )
            } else {
                _screenState.value = _screenState.value.copy(
                    error = true,
                    errorMessage = "Error getting user name",
                )
            }

        }
    }

    fun getFoeName() {
        _screenState.value = _screenState.value.copy(
            loading = true
        )
        viewModelScope.launch {

            try {
                val response = withContext(Dispatchers.IO) {
                    gameRepository.getName().data?.results?.get(0)
                }

                if (response != null) {
                    _screenState.value = _screenState.value.copy(
                        currentFoeName = response,
                        loading = false,
                        error = false,
                        errorMessage = "",
                    )
                } else {
                    _screenState.value = _screenState.value.copy(
                        loading = false,
                        error = true,
                        errorMessage = "Error getting foe name",
                    )
                }
            } catch (e: Exception) {
                _screenState.value = _screenState.value.copy(
                    loading = false,
                    error = true,
                    errorMessage = "Network error: ${e.message}",
                )
            }
        }
    }
}