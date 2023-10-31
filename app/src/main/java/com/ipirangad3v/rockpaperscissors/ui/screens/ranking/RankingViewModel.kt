package com.ipirangad3v.rockpaperscissors.ui.screens.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipirangad3v.rockpaperscissors.data.local.ranking.RankingDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val rankingDatabase: RankingDatabase,
) : ViewModel() {

    init {
        getAllMatches()
    }

    private fun getAllMatches() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = rankingDatabase.rankingDao().getAllMatches()
                Log.d("TAG", "getAllMatches: $result")
            }
        }
    }

}
