package com.apptikar.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.apptikar.common.Destinations
import com.apptikar.common.utils.ConnectivityObserver
import com.apptikar.login.admin.presentation.Login


fun NavGraphBuilder.loginScreen(
    navController: NavHostController,
    connectivityStatus:ConnectivityObserver.Status,
    navigateTo:String
) {
    navigation(
        route = Destinations.LoginGraph,
        startDestination = Destinations.login
    ){
        composable(route = Destinations.login,
            deepLinks = listOf(navDeepLink { uriPattern = "sahl://Login" })
        ) {
            Login(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                navController = navController,
                connectivityStatus = connectivityStatus,
                navigateTo = navigateTo
            )
        }
    }

}