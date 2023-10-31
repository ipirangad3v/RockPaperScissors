package com.ipirangad3v.rockpaperscissors.ui.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipirangad3v.rockpaperscissors.data.local.datastore.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
) : ViewModel() {

    private val _screenState = MutableStateFlow(WelcomeScreenState())
    val screenState = _screenState.asStateFlow() // publi

    init {
        getUserName()
    }


    private fun getUserName() {
        viewModelScope.launch {
            _screenState.value = _screenState.value.copy(
                currentUserName = preferencesDataStore.readUserName().data ?: ""
            )
        }
    }

    fun updateUserName(name: String) {
        viewModelScope.launch {
            if (_screenState.value.isUpdatingUserName) return@launch
            _screenState.value = _screenState.value.copy(
                currentUserName = preferencesDataStore.storeUsername(name).data ?: "",
                isUpdatingUserName = true
            )
            _screenState.value = _screenState.value.copy(
                isUpdatingUserName = false
            )
        }

    }
}