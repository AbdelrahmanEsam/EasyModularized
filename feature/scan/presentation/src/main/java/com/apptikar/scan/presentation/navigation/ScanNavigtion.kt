package com.apptikar.scan.presentation.navigation

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
import com.apptikar.scan.presentation.done.DoneDialog
import com.apptikar.scan.presentation.money.Money
import com.apptikar.scan.presentation.scan.Scan



fun NavGraphBuilder.scanScreen(
    navController: NavHostController,
) {
    navigation(
        route = Destinations.ScanAndMoneyGraph,
        startDestination = Destinations.Scan
    ){
        composable(route = Destinations.Scan,
            deepLinks = listOf(navDeepLink { uriPattern = "sahl://Scan" })
        ) {
            Scan(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                navController = navController,
            )
        }
    }

    composable(route = Destinations.Money,
    ) {
        Money(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(
                    rememberScrollState()
                ),
            navController = navController,
        )
    }
}

