package com.ipirangad3v.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ipirangad3v.rockpaperscissors.ui.theme.RockPaperScissorsTheme
import com.ipirangad3v.rockpaperscissors.ui.screens.game.GAME_SCREEN
import com.ipirangad3v.rockpaperscissors.ui.screens.game.GameScreen
import com.ipirangad3v.rockpaperscissors.ui.screens.ranking.RANKING_SCREEN
import com.ipirangad3v.rockpaperscissors.ui.screens.ranking.RankingScreen
import com.ipirangad3v.rockpaperscissors.ui.screens.welcome.WELCOME_SCREEN
import com.ipirangad3v.rockpaperscissors.ui.screens.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RockPaperScissorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = WELCOME_SCREEN) {
                        composable(WELCOME_SCREEN) {
                            WelcomeScreen(hiltViewModel(), onRankingClick = {
                                navController.navigate(RANKING_SCREEN)
                            }) {
                                navController.navigate(GAME_SCREEN)
                            }
                        }
                        composable(GAME_SCREEN) {
                            GameScreen(hiltViewModel()) {
                                navController.navigateUp()
                            }
                        }
                        composable(RANKING_SCREEN) {
                            RankingScreen(hiltViewModel()) {
                                navController.navigateUp()
                            }
                        }
                    }

                }
            }
        }
    }
}