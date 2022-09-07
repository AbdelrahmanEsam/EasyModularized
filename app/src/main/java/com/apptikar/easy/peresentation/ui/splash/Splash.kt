package com.apptikar.easy.peresentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.apptikar.dribbox.utils.navigateSingleTopTo
import com.apptikar.dribbox.utils.sdp
import com.apptikar.easy.R
import com.apptikar.easy.peresentation.navigation.Destinations
import kotlinx.coroutines.delay


@Composable
fun Splash(modifier: Modifier, navController: NavHostController) {

    LaunchedEffect(true){
            delay(2000L)
            navController.navigateSingleTopTo(Destinations.Login)
    }

    Column(modifier = modifier,Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.easy),
            contentDescription = stringResource(id = R.string.logo),
            modifier = Modifier
                .size(75.sdp)
                .background(Color.White)
                .align(Alignment.CenterHorizontally)

        )
    }







}