package com.apptikar.easy_read.presentation.navigation

import android.nfc.Tag
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apptikar.common.Destinations
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.common.utils.ScreenClassifier
import com.apptikar.easy_read.presentation.Splash
import com.apptikar.login.admin.navigation.loginScreen
import com.apptikar.scan.presentation.navigation.scanScreen
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun EasyNavGraph(
    screenClassifier: ScreenClassifier,
    navController: NavHostController,
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    connectivityStatus: ConnectivityObserver.Status,
) {



    NavHost(
        navController = navController,
        startDestination = Destinations.Splash,
        modifier = modifier
    ) {

        loginScreen(
            navController = navController,
            connectivityStatus=  connectivityStatus,
            navigateTo = "sahl://Scan"
        )

        scanScreen(navController)
        composable(Destinations.Splash){
            Splash(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                ,navController)
        }

    }


}