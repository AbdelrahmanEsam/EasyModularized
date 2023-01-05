package com.apptikar.easy_write.peresentation.navigation

import android.nfc.Tag
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apptikar.common.Destinations
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.ScreenClassifier
import com.apptikar.easy_write.peresentation.ui.splash.Splash
import com.apptikar.navigation.loginScreen
import com.apptikar.writeontag.navigation.writeOnTagScreen
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun EasyNavGraph(
    screenClassifier: ScreenClassifier,
    navController: NavHostController,
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    connectivityStatus: ConnectivityObserver.Status,
    tag : MutableStateFlow<Tag?>
    ) {



    NavHost(
        navController = navController,
        startDestination = Destinations.Splash,
        modifier = modifier
    ) {

        loginScreen(
            navController = navController,
          connectivityStatus=  connectivityStatus,
            "sahl://InsertTheMount"
        )
          writeOnTagScreen(navController = navController,
              connectivityStatus = connectivityStatus,
              tag = tag

          )

        composable(Destinations.Splash){
            Splash(modifier = Modifier
                .fillMaxSize()
                .background(White)
                ,navController)
        }












    }


}