package com.ipirangad3v.rockpaperscissors.ui.screens.welcome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
        LottieAnimation(
            modifier = Modifier
                .size(100.dp)
                .clickable { onRankingClick() },
            composition = ranking,
            iterations = LottieConstants.IterateForever,

            )
        Button(
            onClick = { onStartGameClick() },
        ) {
            Text(text = stringResource(id = R.string.start_game))
        }
    }

}

@Composable
fun WelcomeHeader() {
    Column {
        Text(text = stringResource(id = R.string.welcome_user))
    }

}
