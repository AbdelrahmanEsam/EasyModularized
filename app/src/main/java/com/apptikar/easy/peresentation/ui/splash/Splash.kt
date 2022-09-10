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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.apptikar.easy.common.utils.navigateSingleTopTo
import com.apptikar.dribbox.utils.sdp
import com.apptikar.easy.R
import com.apptikar.easy.common.utils.navigateToInclusive
import com.apptikar.easy.common.utils.readToken
import com.apptikar.easy.peresentation.navigation.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable
fun Splash(modifier: Modifier, navController: NavHostController) {

    val context = LocalContext.current
    LaunchedEffect(true){
            delay(2000L)
        var token :String?
        withContext(Dispatchers.IO){
            token = context.readToken("tokenKey")
        }

        if (token.isNullOrEmpty()){
            navController.navigateToInclusive(Destinations.Login)

        }else{
            navController.navigateToInclusive(Destinations.InsertTheMount)
        }

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