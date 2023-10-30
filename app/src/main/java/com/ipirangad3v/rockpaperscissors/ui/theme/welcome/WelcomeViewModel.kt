package com.ipirangad3v.rockpaperscissors.ui.theme.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipirangad3v.rockpaperscissors.data.local.datastore.PreferencesDataStore
import com.ipirangad3v.rockpaperscissors.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    gameRepository: GameRepository,
    private val preferencesDataStore: PreferencesDataStore,
) : ViewModel() {

    init {
        getUserName()
        gameRepository.getName()
        gameRepository.getPlay()
    }


    private fun getUserName() {
        viewModelScope.launch {
            preferencesDataStore.readUserName()


        }
    }
}