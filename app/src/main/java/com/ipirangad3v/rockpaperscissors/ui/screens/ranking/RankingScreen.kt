package com.ipirangad3v.rockpaperscissors.ui.screens.ranking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ipirangad3v.rockpaperscissors.R
import com.ipirangad3v.rockpaperscissors.data.local.ranking.entities.MatchEntity
import com.ipirangad3v.rockpaperscissors.ui.components.EmptyScreen
import com.ipirangad3v.rockpaperscissors.ui.components.Loading


@Composable
fun RankingScreen(rankingViewModel: RankingViewModel, onBackClicked: () -> Unit) {

    val screenState by rankingViewModel.screenState.collectAsState(RankingScreenState())

    Box(modifier = Modifier.fillMaxSize()) {
        if (screenState.loading) {
            Loading(stringResource(id = R.string.loading_ranking))


        } else {
            Column(
                Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BackButton {
                    onBackClicked()
                }
                if (screenState.matches.isEmpty()) {
                    EmptyScreen()
                } else {
                    RankingList(matches = screenState.matches)


                }
            }
        }
    }
}

@Composable
fun RankingList(matches: List<MatchEntity>) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        matches.forEach { match ->
            MatchItem(match)
        }
    }

}

@Composable
fun MatchItem(match: MatchEntity) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = match.player)
        Text(text = match.cpu)
    }

}

@Composable
fun BackButton(onBackClicked: () -> Unit) {
    Text(
        text = stringResource(id = R.string.back),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onBackClicked() },
        color = androidx.compose.ui.graphics.Color.Red,
        fontSize = 20.sp
    )
}
