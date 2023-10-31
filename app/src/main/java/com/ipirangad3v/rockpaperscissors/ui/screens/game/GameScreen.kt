package com.ipirangad3v.rockpaperscissors.ui.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ipirangad3v.rockpaperscissors.R
import com.ipirangad3v.rockpaperscissors.domain.models.GameState
import com.ipirangad3v.rockpaperscissors.domain.models.Movement
import com.ipirangad3v.rockpaperscissors.ui.components.ErrorScreen
import com.ipirangad3v.rockpaperscissors.ui.components.Loading
import com.ipirangad3v.rockpaperscissors.ui.screens.ranking.BackButton

@Composable
fun GameScreen(gameViewModel: GameViewModel, onBackClicked: () -> Unit) {

    val screenState by gameViewModel.screenState.collectAsState(GameScreenState())

    Box(modifier = Modifier.fillMaxSize()) {
        if (screenState.loading) {
            Loading()


        } else {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BackButton {
                    onBackClicked()
                }
                if (screenState.error) {
                    ErrorScreen {
                        gameViewModel.getFoeName()
                    }
                } else {
                    MatchInfo(screenState.currentUserName, screenState.currentFoeName)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    gameViewModel.getPlay(
                                        Movement.ROCK
                                    )
                                },
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.stones),
                        )
                        Image(

                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    gameViewModel.getPlay(
                                        Movement.PAPER
                                    )
                                },
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.papers1)
                        )
                        Image(
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    gameViewModel.getPlay(
                                        Movement.SCISSORS
                                    )
                                },
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.scissors2)
                        )
                    }
                    screenState.rounds.forEach {
                        Text(text = stringResource(id = R.string.round) + " " + it.currentRound.toString())
                        Text(
                            text = it.result, color = when (it.result) {
                                "Win"  -> androidx.compose.ui.graphics.Color.Green
                                "Lose" -> androidx.compose.ui.graphics.Color.Red
                                else   -> androidx.compose.ui.graphics.Color.Gray
                            }
                        )
                    }
                    screenState.finalResult?.let {
                        Text(text = stringResource(id = R.string.final_result))
                        Text(
                            text = it, color = when (it) {
                                "Win"  -> androidx.compose.ui.graphics.Color.Green
                                "Lose" -> androidx.compose.ui.graphics.Color.Red
                                else   -> androidx.compose.ui.graphics.Color.Gray
                            }
                        )
                        Button(onClick = { gameViewModel.resetGame() }) {
                            Text(text = stringResource(id = R.string.play_again))

                        }
                    }
                }
            }
        }


    }
}

@Composable
fun MatchInfo(currentUserName: String, currentFoeName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = currentUserName)
        Text(text = "vs")
        Text(text = currentFoeName)
    }

}

