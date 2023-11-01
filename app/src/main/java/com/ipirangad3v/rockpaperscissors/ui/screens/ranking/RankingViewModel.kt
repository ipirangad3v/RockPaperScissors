package com.ipirangad3v.rockpaperscissors.ui.screens.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val rankingDatabase: RankingDatabase,
) : ViewModel() {

    private val _screenState = MutableStateFlow(RankingScreenState()) // private mutable state flow
    val screenState = _screenState.asStateFlow() // publi


    init {
        getAllMatches()
    }

    private fun getAllMatches() {
        _screenState.value = _screenState.value.copy(
            loading = true
        )
        viewModelScope.launch {
            rankingDatabase.rankingDao().getAllMatches().collect {
                _screenState.value = _screenState.value.copy(
                    loading = false,
                    matches = it
                )
            }

        }
    }

}
