package com.ipirangad3v.rockpaperscissors.ui.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipirangad3v.rockpaperscissors.data.local.datastore.PreferencesDataStore
import com.ipirangad3v.rockpaperscissors.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val dataStore: PreferencesDataStore,
) : ViewModel() {

    private val _screenState = MutableStateFlow(GameScreenState()) // private mutable state flow
    val screenState = _screenState.asStateFlow() // publi

    init {
        getUserName()
        getFoeName()
    }


    private fun getPlay() {
        viewModelScope.launch {
            when (gameRepository.getPlay().data?.cpu) {
                "rock"     -> {
                }

                "paper"    -> {
                }

                "scissors" -> {
                }
            }
        }
    }

    private fun getUserName() {
        viewModelScope.launch {
            val response = dataStore.readUserName().data
            _screenState.value = _screenState.value.copy(
                currentUserName = response ?: ""
            )
        }
    }

    private fun getFoeName() {
        viewModelScope.launch {
            _screenState.value = _screenState.value.copy(
                loading = true
            )
            val response = gameRepository.getName().data
            _screenState.value = _screenState.value.copy(
                currentFoeName = response?.results?.get(0) ?: ""
            )
            Log.d("GameViewModel", "getFoeName: $response")
        }
    }
}