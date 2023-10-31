package com.ipirangad3v.rockpaperscissors.ui.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ipirangad3v.rockpaperscissors.R

@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel,
    onRankingClick: () -> Unit,
    onStartGameClick: () -> Unit,
) {

    val gameLogo by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rock_paper_scissors))
    val ranking by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ranking))
    val startButton by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.start_button))


    val screenState by welcomeViewModel.screenState.collectAsState(WelcomeScreenState())

    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        WelcomeHeader()
        TextField(
            keyboardOptions = KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Done,
            ), singleLine = true, value = screenState.currentUserName, onValueChange = {
                if (screenState.isUpdatingUserName.not()) welcomeViewModel.updateUserName(it)
            })
        LottieAnimation(
            composition = gameLogo,
            iterations = LottieConstants.IterateForever,
            speed = 0.5f,
        )
        Button(
            onClick = onRankingClick,
            colors = buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            )
        ) {
            LottieAnimation(
                composition = ranking,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(100.dp)
            )
        }
        Button(
            onClick = onStartGameClick,
            colors = buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            )
        ) {
            LottieAnimation(
                composition = startButton,
                iterations = LottieConstants.IterateForever,

                )
        }
    }

}

@Composable
fun WelcomeHeader() {
    Column {
        Text(text = stringResource(id = R.string.welcome_user))
    }

}
